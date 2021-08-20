import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMstService } from 'app/services/rest/app-mst.service';

@Component({
  selector: 'jhi-app-material-list',
  templateUrl: './app-material-list.component.html',
  styleUrls: ['./app-material-list.component.scss'],
})
export class AppMaterialListComponent implements OnInit {
  appId: number | undefined;
  constructor(private appMstService: AppMstService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
  }
}
