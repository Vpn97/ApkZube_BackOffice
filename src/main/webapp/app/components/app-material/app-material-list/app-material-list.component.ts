import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMaterialDTO } from 'app/services/model/app-material-dto.model';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { MaterialService } from 'app/services/rest/material.service';

@Component({
  selector: 'jhi-app-material-list',
  templateUrl: './app-material-list.component.html',
  styleUrls: ['./app-material-list.component.scss'],
})
export class AppMaterialListComponent implements OnInit {
  appId: number | undefined;
  isLoading = false;
  matList: AppMaterialDTO[] | null = [];
  appMst: AppMst | undefined;

  constructor(
    private materialService: MaterialService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
    this.appMstService.findAppMstById(this.appId!).subscribe(data => {
      this.appMst = data as AppMst;
    });

    this.loadMaterial();
  }

  loadMaterial(): void {
    this.materialService.findAllByAppId(this.appId!).subscribe(data => {
      this.matList = data;
    });
  }

  addMaterial(): void {
    if (this.appId) {
      this.router.navigate(['../../', 'add', this.appId], { relativeTo: this.route });
    }
  }

  openMaterial(url: string | undefined): void {
    window.open(url, '_blank');
  }
}
