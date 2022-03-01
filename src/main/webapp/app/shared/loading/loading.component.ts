import { Component, OnInit } from '@angular/core';
import { LoaderService } from './loader.service';

@Component({
  selector: 'jhi-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss'],
})
export class LoadingComponent implements OnInit {
  loading: boolean | undefined;

  constructor(private loaderService: LoaderService) {
    this.loaderService.isLoading.subscribe(v => {
      this.loading = v;
    });
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
