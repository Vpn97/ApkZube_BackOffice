import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { TutorialCategoryMstDTO } from 'app/services/model/tut-cat-mst-dto.model';
import { TutorialCategoryMst } from 'app/services/model/tutorial-cat-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';
import { TutorialService } from 'app/services/rest/tutorial.service';

@Component({
  selector: 'jhi-tutorial-list',
  templateUrl: './tutorial-list.component.html',
  styleUrls: ['./tutorial-list.component.scss'],
})
export class TutorialListComponent implements OnInit {
  appId: number | undefined;
  appMst: AppMst | undefined;
  tabs: TutorialCategoryMstDTO[] | null = null;
  active = 1;
  isLoading = true;

  constructor(
    private tutorialService: TutorialService,
    private categoryService: CategoryService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
    this.appMstService.findAppMstById(this.appId!).subscribe(app => {
      this.appMst = app as AppMst;
      this.loadAll();
    });
  }

  loadAll(): void {
    this.isLoading = true;
    this.tutorialService.categoryWithTutorialList(this.appId!).subscribe(data => {
      this.tabs = data as unknown as TutorialCategoryMstDTO[];
      this.isLoading = false;
    });
  }
}
