import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { ErrorDTO } from 'app/services/model/error-dto.model';
import { TutorialCategoryType } from 'app/services/model/tutorial-category-type.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';

@Component({
  selector: 'jhi-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss'],
})
export class AddCategoryComponent implements OnInit {
  appId: number | undefined;
  appMst: AppMst | undefined;

  errors: ErrorDTO[] | null = null;
  isSaving = false;
  imgFileName = 'Choose backgroid image';
  iconFileName = 'Choose icon';

  iconFileSrc: string | ArrayBuffer | null | undefined;
  imgFileSrc: string | ArrayBuffer | null | undefined;

  tutCatTypes: TutorialCategoryType[] | null = null;

  catForm = this.fb.group({
    categoryName: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(50)]],
    categoryDesc: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(200)]],
    catType: ['', [Validators.required]],
    active: [true, [Validators.required]],
    appId: ['', [Validators.required]],
    imgURL: ['', [Validators.required]],
    iconURL: ['', [Validators.required]],
    iconFile: ['', [Validators.required]],
    imgFile: ['', [Validators.required]],
  });

  constructor(
    private categoryService: CategoryService,
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

    this.loadCategoryTypes();
  }

  loadCategoryTypes(): void {
    this.categoryService.fetchTutCatTypes().subscribe(data => {
      this.tutCatTypes = data as TutorialCategoryType[];
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

  onImgFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file: File = event.target.files[0];
      this.catForm.patchValue({
        imgFile: file,
      });

      this.imgFileName = file.name;
      const reader = new FileReader();
      reader.onload = () => (this.imgFileSrc = reader.result);

      reader.readAsDataURL(file);
    } else {
      this.imgFileName = 'Choose backgroid image';
      this.imgFileSrc = '';
      this.catForm.patchValue({
        imgFile: null,
      });
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    // eslint-disable-next-line no-empty
    if (this.catForm.valid) {
      const formData: FormData = new FormData();

      formData.append('appId', this.catForm.get('appId')?.value);
      formData.append('categoryName', this.catForm.get('categoryName')?.value);
      formData.append('categoryDesc', this.catForm.get('categoryDesc')?.value);
      formData.append('catType', this.catForm.get('catType')?.value);
      formData.append('active', this.catForm.get('active')?.value);
      formData.append('imgFile', this.catForm.get('imgFile')?.value);
      formData.append('iconFile', this.catForm.get('iconFile')?.value);

      // const catMst:TutorialCategoryMst=new TutorialCategoryMst(0,this.catForm.get("appId")?.value,
      // this.catForm.get('categoryName')?.value,this.catForm.get('categoryDesc')?.value,this.catForm.get('catType')?.value,
      // '','',0,new Date(),'',null,this.catForm.get('active')?.value);

      this.categoryService.createTutorialCategory(formData).subscribe(() => {
        this.previousState();
      });
    }
  }

  clearForm(): void {
    this.catForm.reset();

    this.catForm.get('appId')?.setValue(this.appId);

    this.catForm.get('categoryName')?.setValue('');
    this.catForm.get('categoryDesc')?.setValue('');
    this.catForm.get('catType')?.setValue('');
    this.catForm.get('active')?.setValue(true);

    this.catForm.get('imgURL')?.setValue('');

    this.catForm.get('iconURL')?.setValue('');

    this.catForm.get('iconFile')?.setValue('');
    this.catForm.get('imgFile')?.setValue('');
    this.imgFileName = 'Choose backgroid image';
    this.iconFileName = 'Choos icon';
    this.iconFileSrc = '';
    this.imgFileSrc = '';
  }
}
