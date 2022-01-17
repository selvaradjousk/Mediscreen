import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AssessmentService } from '../../services/assessment.service';

@Component({
  selector: 'app-diabete-report',
  templateUrl: './assessment.component.html',
  styleUrls: ['./assessment.component.scss'],
})
export class AssessmentComponent implements OnInit {
  report: any;
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
    private route: ActivatedRoute,
    private assessmentService: AssessmentService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.assessmentService
      .generateReport(+id)
      .pipe(takeUntil(this.destroy$))
      .subscribe((report) => {
        this.report = report;
      });
  }

  getRiskLevelColor(): string {
    switch (this.report.diabetesRiskLevel) {
      case 'None': {
        return '#d9e1f2';
        break;
      }
      case 'Borderline': {
        return '#c6e0b4';
        break;
      }
      case 'In danger': {
        return '#ffe699';
        break;
      }
      case 'Early onset': {
        return '#ff9999';
        break;
      }
      default: {
        return '';
        break;
      }
    }
  }
}
