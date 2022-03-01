import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { ProgramCategoryDTO } from 'app/services/model/program-category-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { ExamplesService } from 'app/services/rest/examples.service';
@Component({
  selector: 'jhi-example-category',
  templateUrl: './example-category.component.html',
  styleUrls: ['./example-category.component.scss'],
})
export class ExampleCategoryComponent implements OnInit {
  isLoading = false;
  appMst: AppMst | undefined;
  catList: ProgramCategoryDTO[] | null = null;
  appId: number | undefined;
  constructor(
    private exampleService: ExamplesService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
    this.appMstService.findAppMstById(this.appId!).subscribe(data => {
      this.appMst = data as AppMst;
    });

    this.loadAll();
  }

  loadAll(): void {
    this.isLoading = true;
    this.exampleService.categories(this.appId!).subscribe(data => {
      this.catList = data as unknown as ProgramCategoryDTO[];

      this.isLoading = false;
    });
  }

  addCategory(): void {
    if (this.appId) {
      this.router.navigate(['../../', 'add-category', this.appId], { relativeTo: this.route });
    }
  }
}
