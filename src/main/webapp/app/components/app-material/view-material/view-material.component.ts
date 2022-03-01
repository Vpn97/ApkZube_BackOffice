import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMaterialDTO } from 'app/services/model/app-material-dto.model';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { MaterialService } from 'app/services/rest/material.service';

@Component({
  selector: 'jhi-view-material',
  templateUrl: './view-material.component.html',
  styleUrls: ['./view-material.component.scss'],
})
export class ViewMaterialComponent implements OnInit {
  matId: number | undefined;

  matDTO: AppMaterialDTO | undefined;
  appMst: AppMst | undefined;

  constructor(
    private materialService: MaterialService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.matId = this.route.snapshot.params['matId'];
    if (this.matId) {
      this.materialService.findByMatId(this.matId).subscribe(data => {
        this.matDTO = data;
        this.appMstService.findAppMstById(this.matDTO.appId!).subscribe(appMstData => {
          this.appMst = appMstData;
        });
      });
    }
  }

  previousState(): void {
    window.history.back();
  }

  openMaterial(url: string | undefined): void {
    window.open(url, '_blank');
  }
}
