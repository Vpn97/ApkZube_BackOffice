import { Component } from '@angular/core';

@Component({
  selector: 'fixedplugin-cmp',
  templateUrl: './fixedplugin.component.html',
})
export class FixedPluginComponent {
  public sidebarColor = 'white';
  public sidebarActiveColor = 'danger';
  isSyncAnimated = false;
  public state = true;

  changeSidebarColor(color: string): void {
    const sidebar = <HTMLElement>document.querySelector('.sidebar');

    this.sidebarColor = color;
    sidebar.setAttribute('data-color', color);
  }
  changeSidebarActiveColor(color: string): void {
    const sidebar = <HTMLElement>document.querySelector('.sidebar');
    this.sidebarActiveColor = color;
    sidebar.setAttribute('data-active-color', color);
  }
}
