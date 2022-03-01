import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { AppMaterialDTO } from '../model/app-material-dto.model';
import { AppMaterialType } from '../model/app-material-type.model';

@Injectable({
  providedIn: 'root',
})
export class MaterialService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  findAllByAppId(appId: number): Observable<AppMaterialDTO[]> {
    return this.http.get<AppMaterialDTO[]>(this.applicationConfigService.getEndpointFor('api/material/findAllByAppId'), {
      params: { appId },
    });
  }

  findByMatId(matId: number): Observable<AppMaterialDTO> {
    return this.http.get<AppMaterialDTO>(this.applicationConfigService.getEndpointFor('api/material/findByMatId'), {
      params: { matId },
    });
  }

  fetchMaterialTypeCodes(): Observable<AppMaterialType[]> {
    return this.http.get<AppMaterialType[]>(this.applicationConfigService.getEndpointFor('api/material/fetchMaterialTypeCodes'));
  }

  addMaterial(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/material/addMaterial'), formData);
  }

  updateMaterial(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/material/updateMaterial'), formData);
  }
}
