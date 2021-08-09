import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppMst } from '../../../services/model/app-mst.model';
import { AppMstService } from '../../../services/rest/app-mst.service';

@Component({
  selector: 'jhi-app-mst',
  templateUrl: './app-mst-list.component.html',
  styleUrls: ['./app-mst-list.component.scss'],
})
export class AppMstListComponent implements OnInit {
  appMstList: AppMst[] | null = null;

  constructor(private appMstService: AppMstService, private router: Router) {}

  ngOnInit(): void {
    this.loadApplicationInfo();
  }

  oppenAppLink(appMst: AppMst): void {
    window.open(appMst.appLink, '_blank');
  }

  oppenApp(appMst: AppMst): void {
    // this.router.navigate(['dashboard/app-mst-list',{appMst}]);
    this.router.navigateByUrl('/dashboard/app-mst-list', { state: appMst });
  }

  loadApplicationInfo(): void {
    this.appMstService.getAllAppMstDTOs().subscribe(response => {
      this.appMstList = response as AppMst[];
    });
  }
}
