export interface ITutorialCategoryType {
  catTypeId?: number;
  catTypeCode?: string;
  type?: string;
}

export class TutorialCategoryType implements ITutorialCategoryType {
  constructor(public catTypeId?: number, public catTypeCode?: string, public type?: string) {}
}
