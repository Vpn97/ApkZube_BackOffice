import { ProgrammingLangDTO } from './programming-lang-dto.model';

export interface IAppMst {
  appId?: number;
  appName?: string;
  appCode?: string;
  packageName?: string;
  appPrice?: string;
  appSize?: string;
  appLink?: string;
  iconUrl?: string;
  developerName?: null;
  privacyPolicyUrl?: string;
  created_date?: Date;
  programmingLangDTO?: ProgrammingLangDTO;
}

export class AppMst implements IAppMst {
  constructor(
    public appId?: number,
    public appName?: string,
    public appCode?: string,
    public packageName?: string,
    public appPrice?: string,
    public appSize?: string,
    public appLink?: string,
    public iconUrl?: string,
    public developerName?: null,
    public privacyPolicyUrl?: string,
    public created_date?: Date,
    public programmingLangDTO?: ProgrammingLangDTO
  ) {}
}
