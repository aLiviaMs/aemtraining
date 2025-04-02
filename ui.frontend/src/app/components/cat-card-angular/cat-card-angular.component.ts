import { MapTo } from '@adobe/aem-angular-editable-components';
import { Component, Input, OnInit } from '@angular/core';
import { finalize, take } from 'rxjs/operators';
import { CatDTO } from 'src/app/models/interfaces/CatDTO';
import { CatCardService } from 'src/app/services/cat-card.service';

const BasicComponentEditConfig = {
  emptyLabel: 'Cat Card Angular',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1,
}

@Component({
  selector: 'app-cat-card-angular',
  templateUrl: './cat-card-angular.component.html',
  styleUrls: ['./cat-card-angular.component.css']
})
export class CatCardAngularComponent implements OnInit {
  @Input() title: string;
  @Input() disabled: boolean;
  @Input() count: number;

  cats: CatDTO[] = [];
  loading = false;

  constructor(private readonly catCardService: CatCardService) {}

  public ngOnInit(): void {
    this.fetchCatImage();
  }

  public fetchCatImage(): void {
    this.loading = true;

    this.catCardService.getCatImages(this.count).pipe(
      take(1),
      finalize(() => this.loading = false)
    ).subscribe(
      (data: CatDTO[]) => {
        this.cats = data;
      }
    );
  }
}

MapTo('aemtraining/components/cat-card-angular')(
  CatCardAngularComponent,
  BasicComponentEditConfig
);