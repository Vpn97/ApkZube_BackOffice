import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { ProgramCategoryDTO } from 'app/services/model/program-category-dto.model';
import { ProgramMstDTO } from 'app/services/model/program-mst-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { ExamplesService } from 'app/services/rest/examples.service';

@Component({
  selector: 'jhi-example-list',
  templateUrl: './example-list.component.html',
  styleUrls: ['./example-list.component.scss'],
})
export class ExampleListComponent implements OnInit {
  isLoading = false;
  appMst: AppMst | undefined;
  programList: ProgramMstDTO[] | null = [];
  appId: number | undefined;
  catId: number | undefined;
  catDTO: ProgramCategoryDTO | undefined;

  constructor(
    private exampleService: ExamplesService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.catId = this.route.snapshot.params['catId'];
    this.appId = this.route.snapshot.params['appId'];

    this.appMstService.findAppMstById(this.appId!).subscribe(app => {
      this.appMst = app as AppMst;
    });
    this.exampleService.category(this.catId!).subscribe(cat => {
      this.catDTO = cat as unknown as ProgramCategoryDTO;
    });

    this.loadAll();
  }

  loadAll(): void {
    this.isLoading = true;
    this.exampleService.getExamplesByCategory(this.catId!).subscribe(list => {
      this.programList = list as unknown as ProgramMstDTO[];
      this.isLoading = false;
    });
  }
}
