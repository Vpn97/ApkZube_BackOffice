import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { TutorialCategoryMstDTO } from '../model/tut-cat-mst-dto.model';
import { TutorialCategoryMst } from '../model/tutorial-cat-mst.model';
import { ITutorialCategoryType } from '../model/tutorial-category-type.model';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAllTutCategoryByAppId(appId: number): Observable<TutorialCategoryMst[]> {
    return this.http.get<TutorialCategoryMst[]>(this.applicationConfigService.getEndpointFor('api/category/getAllTutCategoryByAppId'), {
      params: { appId },
    });
  }

  getTutCategoryById(catId: number): Observable<TutorialCategoryMstDTO> {
    return this.http.get<TutorialCategoryMstDTO>(this.applicationConfigService.getEndpointFor('api/category/getTutCategoryById'), {
      params: { catId },
    });
  }

  fetchTutCatTypes(): Observable<ITutorialCategoryType> {
    return this.http.get<ITutorialCategoryType>(this.applicationConfigService.getEndpointFor('api/category/fetchTutCatTypes'));
  }

  createTutorialCategory(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/category/createTutorialCategory'), formData);
  }

  updateTutorialCategory(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/category/updateTutorialCategory'), formData);
  }
}
