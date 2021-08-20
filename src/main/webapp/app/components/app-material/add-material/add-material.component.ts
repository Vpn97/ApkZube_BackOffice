import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMstService } from 'app/services/rest/app-mst.service';

@Component({
  selector: 'jhi-add-material',
  templateUrl: './add-material.component.html',
  styleUrls: ['./add-material.component.scss'],
})
export class AddMaterialComponent implements OnInit {
  matId: number | undefined;
  constructor(private appMstService: AppMstService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.matId = this.route.snapshot.params['matId'];
  }
}
