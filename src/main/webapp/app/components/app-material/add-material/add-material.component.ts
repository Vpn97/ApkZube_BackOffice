import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ActionCodeMst } from 'app/services/model/action-code-mst.model';
import { AppMaterialType } from 'app/services/model/app-material-type.model';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CommonService } from 'app/services/rest/common.service';
import { MaterialService } from 'app/services/rest/material.service';

@Component({
  selector: 'jhi-add-material',
  templateUrl: './add-material.component.html',
  styleUrls: ['./add-material.component.scss'],
})
export class AddMaterialComponent implements OnInit {
  appId: number | undefined;
  appMst: AppMst | undefined;
  isSaving = false;

  iconFileName = 'Choose icon';
  iconFileSrc: string | ArrayBuffer | null | undefined;

  typeCodes: AppMaterialType[] = [];
  actionCodes: ActionCodeMst[] = [];

  matForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(50)]],
    detail: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(200)]],
    typeCode: ['', [Validators.required]],
    active: [true, [Validators.required]],
    appId: ['', [Validators.required]],
    materialURL: ['', [Validators.required]],
    iconURL: ['', [Validators.required]],
    iconFile: ['', [Validators.required]],
    clickActionCode: ['', [Validators.required]],
  });

  constructor(
    private commonService: CommonService,
    private materialService: MaterialService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.appId = this.route.snapshot.params['appId'];
    this.matForm.get('appId')?.setValue(this.appId);

    this.appMstService.findAppMstById(this.appId!).subscribe(data => {
      this.appMst = data as AppMst;
    });

    this.loadActionCode();
    this.loadMaterialTypes();
  }

  loadActionCode(): void {
    this.commonService.fetchActionCodes().subscribe(data => {
      this.actionCodes = data;
    });
  }

  loadMaterialTypes(): void {
    this.materialService.fetchMaterialTypeCodes().subscribe(data => {
      this.typeCodes = data;
    });
  }

  onIconFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file: File = event.target.files[0];
      this.matForm.patchValue({
        iconFile: file,
      });
      this.iconFileName = file.name;

      const reader = new FileReader();
      reader.onload = () => (this.iconFileSrc = reader.result);

      reader.readAsDataURL(file);
    } else {
      this.iconFileName = 'Choose icon';
      this.matForm.patchValue({
        iconFile: null,
      });
      this.iconFileSrc = '';
    }
  }

  save(): void {
    if (this.matForm.valid) {
      this.isSaving = true;
      const formData: FormData = new FormData();

      formData.append('appId', this.matForm.get('appId')?.value);
      formData.append('title', this.matForm.get('title')?.value);
      formData.append('detail', this.matForm.get('detail')?.value);
      formData.append('typeCode', this.matForm.get('typeCode')?.value);
      formData.append('active', this.matForm.get('active')?.value);
      formData.append('materialURL', this.matForm.get('materialURL')?.value);

      formData.append('clickActionCode', this.matForm.get('clickActionCode')?.value);
      formData.append('iconFile', this.matForm.get('iconFile')?.value);

      this.materialService.addMaterial(formData).subscribe(
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

  openMaterial(): void {
    window.open(this.matForm.get('materialURL')?.value, '_blank');
  }

  clearForm(): void {
    this.matForm.reset();

    this.matForm.get('appId')?.setValue(this.appId);

    this.matForm.get('title')?.setValue('');
    this.matForm.get('detail')?.setValue('');
    this.matForm.get('typeCode')?.setValue('');
    this.matForm.get('active')?.setValue(true);
    this.matForm.get('materialURL')?.setValue('');
    this.matForm.get('clickActionCode')?.setValue('');

    this.matForm.get('iconURL')?.setValue('');
    this.matForm.get('iconFile')?.setValue('');
    this.iconFileName = 'Choos icon';
    this.iconFileSrc = '';
  }
}
