import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { Location } from '@angular/common';
import { AppMstInfoDTO } from 'app/services/model/app-mst-info-dto.moedel';

@Component({
  selector: 'jhi-app-mst',
  templateUrl: './app-mst-dtl.component.html',
  styleUrls: ['./app-mst-dtl.component.scss'],
})
export class AppMstDtlComponent implements OnInit {
  appMst: AppMst | undefined;

  appMstInfoDTO!: AppMstInfoDTO;

  constructor(private appMstService: AppMstService, private router: Router, private route: ActivatedRoute, private location: Location) {}

  ngOnInit(): void {
    const appId = this.route.snapshot.params['appId'];

    this.appMstService.getAppMstInfo(appId).subscribe(data => {
      this.appMstInfoDTO = data as AppMstInfoDTO;
      this.appMst = this.appMstInfoDTO.appMst;
    });
  }

  openApp(): void {
    window.open(this.appMst!.appLink, '_blank');
  }

  editAppInfo(): void {
    window.open(this.appMst!.appLink, '_blank');
  }

  editCategory(): void {
    if (!this.router.getCurrentNavigation()) {
      // There were no routing during login (eg from navigationToStoredUrl)
      this.router.navigate(['tut-cat', 'view-category-list', this.appMst?.appId]);
      // this.router.navigate(['/app-mst/view-category-list',this.appMst?.appId]);
    }
  }
}
