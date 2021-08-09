import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { Location } from '@angular/common';

@Component({
  selector: 'jhi-app-mst',
  templateUrl: './app-mst.component.html',
  styleUrls: ['./app-mst.component.scss'],
})
export class AppMstComponent implements OnInit {
  appMst: AppMst | undefined;

  constructor(private appMstService: AppMstService, private router: Router, private route: ActivatedRoute, private location: Location) {}

  ngOnInit(): void {
    this.route.data.subscribe(({ appMst }) => {
      this.appMst = appMst;
    });
  }
}
