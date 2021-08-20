import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { TutorialCategoryMstDTO } from 'app/services/model/tut-cat-mst-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';

@Component({
  selector: 'jhi-view-category',
  templateUrl: './view-category.component.html',
  styleUrls: ['./view-category.component.scss'],
})
export class ViewCategoryComponent implements OnInit {
  tutCatMst: TutorialCategoryMstDTO | undefined;
  appMst: AppMst | undefined;

  constructor(
    private categoryService: CategoryService,
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
      this.categoryService.getTutCategoryById(catId).subscribe(data => {
        this.tutCatMst = data;
        this.appMst = data.appMst;
      });
    }
  }

  previousState(): void {
    window.history.back();
  }
}
