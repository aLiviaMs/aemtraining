import { MapTo } from '@adobe/aem-angular-editable-components';
import { Component, Input, OnInit } from '@angular/core';

const AngularNavigatorComponentEditConfig = {
  emptyLabel: 'Angular Navigator Component',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1,
}

@Component({
  selector: 'app-angular-navigator',
  templateUrl: './angular-navigator.component.html',
  styleUrls: ['./angular-navigator.component.css']
})
export class AngularNavigatorComponent implements OnInit {
  @Input() interestingPages: [];

  constructor() { }

  ngOnInit(): void {
  }
}

MapTo('aemtraining/components/angular-navigator')(
  AngularNavigatorComponent,
  AngularNavigatorComponentEditConfig
);
