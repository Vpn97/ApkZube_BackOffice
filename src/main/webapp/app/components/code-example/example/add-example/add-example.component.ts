import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { ProgramCategoryDTO } from 'app/services/model/program-category-dto.model';
import { ProgramMstDTO } from 'app/services/model/program-mst-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CommonService } from 'app/services/rest/common.service';
import { ExamplesService } from 'app/services/rest/examples.service';

@Component({
  selector: 'jhi-add-example',
  templateUrl: './add-example.component.html',
  styleUrls: ['./add-example.component.scss'],
})
export class AddExampleComponent implements OnInit {
  content: string | undefined;
  mode: string | undefined;
  theme: string | undefined;
  options: any;
  appId: number | undefined;
  programId: number | undefined;
  catId: number | undefined;

  appMst: AppMst | undefined;
  isSaving = false;
  mst: ProgramMstDTO | null = null;

  categorys: ProgramCategoryDTO[] | null = null;

  editorOptions: any;
  model: any;

  programForm = this.fb.group({
    appId: [''],
    programId: [''],
    title: ['', [Validators.required, Validators.minLength(6)]],
    programText: ['', [Validators.required]],
    catId: ['', [Validators.required]],
    outputText: ['', [Validators.required]],
    programInput: [''],
    explainText: ['', [Validators.required]],
    programBy: ['', [Validators.required]],
    programURL: [''],
  });

  constructor(
    private commonService: CommonService,
    private exampleService: ExamplesService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.route.queryParams.subscribe(data => {
      this.appId = data.appId;
      this.catId = data.catId;
      this.programId = data.programId;

      this.loadCategory(this.appId!);
      if (this.appId) {
        this.programForm.get('appId')?.setValue(this.appId);
      }
      if (this.catId) {
        this.programForm.get('catId')?.setValue(this.catId);
      }

      this.appMstService.findAppMstById(this.appId!).subscribe(mstData => {
        this.appMst = mstData as AppMst;
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
        };
      });
    });
  }

  ngOnInit(): void {
    this.clearForm();
  }

  loadCategory(appId: number): void {
    this.exampleService.categories(appId).subscribe(data => {
      this.categorys = data as unknown as ProgramCategoryDTO[];
      if (this.catId) {
        this.programForm.get('catId')?.setValue(this.catId);
      }
    });
  }

  save(): void {
    if (this.programForm.valid) {
      this.isSaving = true;
      const formData: FormData = new FormData();

      formData.append('appId', this.programForm.get('appId')?.value);
      formData.append('programId', this.programForm.get('programId')?.value);
      formData.append('catId', this.programForm.get('catId')?.value);

      formData.append('title', this.programForm.get('title')?.value);
      formData.append('programText', this.programForm.get('programText')?.value);
      formData.append('outputText', this.programForm.get('outputText')?.value);
      formData.append('programInput', this.programForm.get('programInput')?.value);
      formData.append('explainText', this.programForm.get('explainText')?.value);

      formData.append('programBy', this.programForm.get('programBy')?.value);
      formData.append('programURL', this.programForm.get('programURL')?.value);

      this.exampleService.saveProgram(this.mst!).subscribe(
        () => {
          this.previousState();
        },
        () => (this.isSaving = false)
      );
    }
  }

  previousState(): void {
    window.history.back();
  }

  openURL(): void {
    window.open(this.programForm.get('programURL')?.value, '_blank');
  }

  clearForm(): void {
    this.programForm.reset();
    if (this.programId) {
      this.exampleService.programById(this.programId).subscribe(programMst => {
        this.mst = programMst;
      });
    } else {
      this.mst = new ProgramMstDTO(
        this.programId!,
        this.appId!,
        this.catId!,
        '',
        '',
        '',
        '',
        '',
        'ApkZube',
        '',
        '',
        null,
        null,
        null,
        null
      );
    }
  }

  onEditorInit(editor: any): void {
    const line = editor.getPosition();
  }
}
