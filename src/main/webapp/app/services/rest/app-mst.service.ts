import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { IAppMst } from '../model/app-mst.model';

@Injectable({
  providedIn: 'root',
})
export class AppMstService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAllAppMstDTOs(): Observable<IAppMst[]> {
    return this.http.get<IAppMst[]>(this.applicationConfigService.getEndpointFor('api/appmst/getallappinfo'), {});
  }

  findAppMstById(appId: number): Observable<IAppMst> {
    return this.http.get<IAppMst>(this.applicationConfigService.getEndpointFor('api/appmst/findAppMstById'), {});
  }
}
