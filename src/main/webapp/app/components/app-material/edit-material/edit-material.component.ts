import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ActionCodeMst } from 'app/services/model/action-code-mst.model';
import { AppMaterialDTO } from 'app/services/model/app-material-dto.model';
import { AppMaterialType } from 'app/services/model/app-material-type.model';
import { AppMst } from 'app/services/model/app-mst.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CommonService } from 'app/services/rest/common.service';
import { MaterialService } from 'app/services/rest/material.service';

@Component({
  selector: 'jhi-edit-material',
  templateUrl: './edit-material.component.html',
  styleUrls: ['./edit-material.component.scss'],
})
export class EditMaterialComponent implements OnInit {
  matId: number | undefined;

  appId: number | undefined;
  appMst: AppMst | undefined;
  isSaving = false;

  matDTO: AppMaterialDTO | undefined;

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
    matId: ['', [Validators.required]],

    materialURL: ['', [Validators.required]],
    iconURL: ['', [Validators.required]],
    iconFile: [''],
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
    this.matId = this.route.snapshot.params['matId'];

    if (this.matId) {
      this.materialService.findByMatId(this.matId).subscribe(data => {
        this.matDTO = data;
        this.matId = this.matDTO.materialId;
        this.matForm.get('matId')?.setValue(this.matId);

        this.appMstService.findAppMstById(this.matDTO.appId!).subscribe(appMstData => {
          this.appMst = appMstData;
          this.appId = this.appMst.appId;
          this.matForm.get('appId')?.setValue(this.appId);
        });
        this.clearForm();
      });
    }

    this.loadActionCode();
    this.loadMaterialTypes();
  }

  openMaterial(): void {
    window.open(this.matForm.get('materialURL')?.value, '_blank');
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
      formData.append('materialId', this.matForm.get('matId')?.value);

      formData.append('appId', this.matForm.get('appId')?.value);
      formData.append('title', this.matForm.get('title')?.value);
      formData.append('detail', this.matForm.get('detail')?.value);
      formData.append('typeCode', this.matForm.get('typeCode')?.value);
      formData.append('active', this.matForm.get('active')?.value);
      formData.append('materialURL', this.matForm.get('materialURL')?.value);

      formData.append('clickActionCode', this.matForm.get('clickActionCode')?.value);

      if (this.matForm.get('iconFile')?.value) {
        formData.append('iconFile', this.matForm.get('iconFile')?.value);
      }
      this.materialService.updateMaterial(formData).subscribe(
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
    this.matForm.reset();

    this.matForm.get('appId')?.setValue(this.matDTO?.appId);
    this.matForm.get('matId')?.setValue(this.matDTO?.materialId);

    this.matForm.get('title')?.setValue(this.matDTO?.title);
    this.matForm.get('detail')?.setValue(this.matDTO?.detail);
    this.matForm.get('typeCode')?.setValue(this.matDTO?.typeCode);
    this.matForm.get('active')?.setValue(this.matDTO?.active);
    this.matForm.get('materialURL')?.setValue(this.matDTO?.materialURL);
    this.matForm.get('clickActionCode')?.setValue(this.matDTO?.clickActionCode);

    this.matForm.get('iconURL')?.setValue(this.matDTO?.iconURL);
    this.matForm.get('iconFile')?.setValue(null);
    this.iconFileName = 'Choos icon';
    this.iconFileSrc = this.matDTO?.iconURL;
  }
}
