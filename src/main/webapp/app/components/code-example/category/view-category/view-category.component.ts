import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { ProgramCategoryDTO } from 'app/services/model/program-category-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { ExamplesService } from 'app/services/rest/examples.service';

@Component({
  selector: 'jhi-view-category',
  templateUrl: './view-category.component.html',
  styleUrls: ['./view-category.component.scss'],
})
export class ViewCategoryComponent implements OnInit {
  catDTO: ProgramCategoryDTO | undefined;
  appMst: AppMst | undefined;

  constructor(
    private exampleService: ExamplesService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const catId = this.route.snapshot.params['catId'];
    this.loadCategory(catId);
  }

  loadCategory(catId: any): void {
    if (catId) {
      this.exampleService.category(catId).subscribe(data => {
        this.catDTO = data;
        this.appMstService.findAppMstById(this.catDTO.appId).subscribe(app => {
          this.appMst = app as AppMst;
        });
      });
    }
  }

  previousState(): void {
    window.history.back();
  }
}
