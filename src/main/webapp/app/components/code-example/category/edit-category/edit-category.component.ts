import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { ProgramCategoryDTO } from 'app/services/model/program-category-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CommonService } from 'app/services/rest/common.service';
import { ExamplesService } from 'app/services/rest/examples.service';

@Component({
  selector: 'jhi-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss'],
})
export class EditCategoryComponent implements OnInit {
  catId: number | undefined;
  appMst: AppMst | undefined;
  isSaving = false;

  iconFileName = 'Choose icon';
  iconFileSrc: string | ArrayBuffer | null | undefined;

  catDTO: ProgramCategoryDTO | undefined;

  catForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(50)]],
    detail: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(200)]],
    active: [true, [Validators.required]],
    catId: ['', [Validators.required]],
    appId: ['', [Validators.required]],
    iconURL: [''],
    iconFile: [''],
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
    this.catId = this.route.snapshot.params['catId'];
    this.catForm.get('catId')?.setValue(this.catId);

    if (this.catId) {
      this.exampleService.category(this.catId).subscribe(data => {
        this.catDTO = data;
        this.clearForm();
        this.appMstService.findAppMstById(this.catDTO.appId).subscribe(app => {
          this.appMst = app as AppMst;
          this.catForm.get('appId')?.setValue(this.appMst.appId);
        });
      });
    }
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
      this.iconFileSrc = null;
    }
  }

  save(): void {
    if (this.catForm.valid) {
      this.isSaving = true;
      const formData: FormData = new FormData();

      formData.append('appId', this.catForm.get('appId')?.value);
      formData.append('programCatId', this.catForm.get('catId')?.value);

      formData.append('catName', this.catForm.get('title')?.value);
      formData.append('catDescription', this.catForm.get('detail')?.value);
      formData.append('active', this.catForm.get('active')?.value);
      if (this.catForm.get('imgFile')?.value) {
        formData.append('imgFile', this.catForm.get('imgFile')?.value);
      }
      if (this.catForm.get('iconFile')?.value) {
        formData.append('iconFile', this.catForm.get('iconFile')?.value);
      }

      this.exampleService.updateCategory(formData).subscribe(
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
    this.catForm.get('appId')?.setValue(this.appMst?.appId);
    this.catForm.get('catId')?.setValue(this.catDTO?.programCatId);
    this.catForm.get('title')?.setValue(this.catDTO?.catName);
    this.catForm.get('detail')?.setValue(this.catDTO?.catDescription);
    this.catForm.get('active')?.setValue(this.catDTO?.active);
    this.catForm.get('iconURL')?.setValue(this.catDTO?.catIconURL);
    this.catForm.get('iconFile')?.setValue(null);
    this.iconFileName = 'Choos icon';
    this.iconFileSrc = this.catDTO?.catIconURL;
  }
}
