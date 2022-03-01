import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { TutorialCategoryMst } from 'app/services/model/tutorial-cat-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';

@Component({
  selector: 'jhi-view-category',
  templateUrl: './view-category-list.component.html',
  styleUrls: ['./view-category-list.component.scss'],
})
export class ViewCategoryListComponent implements OnInit {
  isLoading = false;
  appMst: AppMst | undefined;
  catList: TutorialCategoryMst[] | null = null;
  appId: number | undefined;
  constructor(
    private categoryService: CategoryService,
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
    this.categoryService.getAllTutCategoryByAppId(this.appId!).subscribe(data => {
      this.catList = data as unknown as TutorialCategoryMst[];

      this.isLoading = false;
    });
  }

  addCategory(): void {
    if (this.appId) {
      this.router.navigate(['../../', 'add-category', this.appId], { relativeTo: this.route });
    }
  }
}
