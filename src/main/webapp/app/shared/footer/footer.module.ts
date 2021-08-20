import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FooterComponent } from './footer.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [RouterModule, CommonModule, FontAwesomeModule],
  declarations: [FooterComponent],
  exports: [FooterComponent],
})
export class FooterModule {}
