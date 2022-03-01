import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { CompilerDTO } from 'app/services/model/compiler-dto.model';
import { ProgramCategoryDTO } from 'app/services/model/program-category-dto.model';
import { ProgramMstDTO } from 'app/services/model/program-mst-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CompilerService } from 'app/services/rest/compiler.service';
import { ExamplesService } from 'app/services/rest/examples.service';

@Component({
  selector: 'jhi-view-example',
  templateUrl: './view-example.component.html',
  styleUrls: ['./view-example.component.scss'],
})
export class ViewExampleComponent implements OnInit {
  content: string | undefined;
  mode: string | undefined;
  theme: string | undefined;
  options: any;

  model: any;

  editorOptions: any;

  codeId: number | undefined;
  catId: number | undefined;
  appId: number | undefined;
  appMst: AppMst | undefined;
  catDTO: ProgramCategoryDTO | undefined;
  codeMst: ProgramMstDTO | undefined;

  output: string | undefined;

  constructor(
    private exampleService: ExamplesService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute,
    private compilerService: CompilerService
  ) {}

  ngOnInit(): void {
    this.codeId = this.route.snapshot.params['codeId'];
    this.appId = this.route.snapshot.params['appId'];
    this.catId = this.route.snapshot.params['catId'];

    this.appMstService.findAppMstById(this.appId!).subscribe(app => {
      this.appMst = app as AppMst;
      this.mode = this.appMst.programmingLangDTO?.langMode;
      this.theme = this.appMst.programmingLangDTO?.langTheme;
      this.options = {
        lineNumbers: true,
        theme: this.theme,
        mode: this.mode,
        mime: this.appMst.programmingLangDTO?.mimeType,
      };

      this.editorOptions = {
        theme: 'vs-dark',
        language: this.mode,
        automaticLayout: true,
        autoIndent: 'advanced',
        matchBrackets: 'always',
        formatOnType: true,
        formatOnPaste: true,
        readOnly: true,
      };
    });

    this.exampleService.category(this.catId!).subscribe(cat => {
      this.catDTO = cat as unknown as ProgramCategoryDTO;
    });

    this.exampleService.programById(this.codeId!).subscribe(code => {
      this.codeMst = code as unknown as ProgramMstDTO;
      this.content = this.codeMst.programText;
      this.output = this.codeMst.outputText;
    });
  }

  previousState(): void {
    window.history.back();
  }
  openURL(): void {
    window.open(this.codeMst!.programURL, '_blank');
  }

  onEditorInit(editor: any): void {
    const line = editor.getPosition();
  }

  run(): void {
    let dto = new CompilerDTO(this.codeMst!.programText, this.appMst?.programmingLangDTO?.langMode, this.codeMst!.programInput!, '', '');
    this.compilerService.python3(dto).subscribe(data => {
      dto = data as CompilerDTO;
      this.output = dto.output;
    });
  }
}
