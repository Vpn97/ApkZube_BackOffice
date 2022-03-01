import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CommonService } from 'app/services/rest/common.service';
import { ExamplesService } from 'app/services/rest/examples.service';

@Component({
  selector: 'jhi-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss'],
})
export class AddCategoryComponent implements OnInit {
  appId: number | undefined;
  appMst: AppMst | undefined;
  isSaving = false;

  iconFileName = 'Choose icon';
  iconFileSrc: string | ArrayBuffer | null | undefined;

  catForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(50)]],
    detail: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(200)]],
    active: [true, [Validators.required]],
    appId: ['', [Validators.required]],
    iconURL: ['', [Validators.required]],
    iconFile: ['', [Validators.required]],
  });

  constructor(
    private commonService: CommonService,
    private exampleService: ExamplesService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
    this.catForm.get('appId')?.setValue(this.appId);

    this.appMstService.findAppMstById(this.appId!).subscribe(data => {
      this.appMst = data as AppMst;
    });
  }

  onIconFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file: File = event.target.files[0];
      this.catForm.patchValue({
        iconFile: file,
      });
      this.iconFileName = file.name;

      const reader = new FileReader();
      reader.onload = () => (this.iconFileSrc = reader.result);

      reader.readAsDataURL(file);
    } else {
      this.iconFileName = 'Choose icon';
      this.catForm.patchValue({
        iconFile: null,
      });
      this.iconFileSrc = '';
    }
  }

  save(): void {
    if (this.catForm.valid) {
      this.isSaving = true;
      const formData: FormData = new FormData();

      formData.append('appId', this.catForm.get('appId')?.value);
      formData.append('catName', this.catForm.get('title')?.value);
      formData.append('catDescription', this.catForm.get('detail')?.value);
      formData.append('active', this.catForm.get('active')?.value);
      formData.append('iconFile', this.catForm.get('iconFile')?.value);

      this.exampleService.addCategory(formData).subscribe(
        () => {
          this.previousState();
        },
        () => (this.isSaving = false)
      );
    }
  }

  previousState(): void {
    window.history.back();
  }

  clearForm(): void {
    this.catForm.reset();

    this.catForm.get('appId')?.setValue(this.appId);

    this.catForm.get('title')?.setValue('');
    this.catForm.get('detail')?.setValue('');
    this.catForm.get('active')?.setValue(true);
    this.catForm.get('iconURL')?.setValue('');
    this.catForm.get('iconFile')?.setValue('');
    this.iconFileName = 'Choos icon';
    this.iconFileSrc = '';
  }
}
