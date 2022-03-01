import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { TutorialCategoryMstDTO } from 'app/services/model/tut-cat-mst-dto.model';
import { TutorialMstDTO } from 'app/services/model/tutorial-mst-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';
import { TutorialService } from 'app/services/rest/tutorial.service';
import { Editor } from 'ngx-editor';

@Component({
  selector: 'jhi-view-tutorial-mst',
  templateUrl: './view-tutorial-mst.component.html',
  styleUrls: ['./view-tutorial-mst.component.scss'],
})
export class ViewTutorialMstComponent implements OnInit, OnDestroy {
  appId: number | undefined;
  tutMstId!: number;

  appMst!: AppMst;
  tutMst!: TutorialMstDTO;
  catMst!: TutorialCategoryMstDTO;

  editor: Editor = new Editor();

  constructor(
    private tutorialService: TutorialService,
    private categoryService: CategoryService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnDestroy(): void {
    this.editor.destroy();
  }

  ngOnInit(): void {
    this.editor = new Editor();
    this.appId = this.route.snapshot.params['appId'];
    this.tutMstId = this.route.snapshot.params['mstId'];
    this.appMstService.findAppMstById(this.appId!).subscribe(app => {
      this.appMst = app as AppMst;
    });

    this.tutorialService.getTutorialMstById(this.tutMstId).subscribe(data => {
      this.tutMst = data;
      this.categoryService.getTutCategoryById(this.tutMst.tutCatMstId).subscribe(catMst => {
        this.catMst = catMst;
      });
    });
  }

  previousState(): void {
    window.history.back();
  }

  openMaterial(url: string | undefined): void {
    window.open(url, '_blank');
  }
}
