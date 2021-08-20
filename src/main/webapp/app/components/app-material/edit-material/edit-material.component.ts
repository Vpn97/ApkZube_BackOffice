import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMstService } from 'app/services/rest/app-mst.service';

@Component({
  selector: 'jhi-edit-material',
  templateUrl: './edit-material.component.html',
  styleUrls: ['./edit-material.component.scss'],
})
export class EditMaterialComponent implements OnInit {
  matId: number | undefined;
  constructor(private appMstService: AppMstService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.matId = this.route.snapshot.params['matId'];
  }
}
