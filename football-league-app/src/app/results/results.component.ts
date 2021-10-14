import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  matches: any[] = [{
    'homeTeam': 'Pusphaira',
    'awayTeam': 'RPC',
    'homeGoals': 1,
    'awayGoals': 0
  },
  {
    'homeTeam': 'Rovinj',
    'awayTeam': 'Porec',
    'homeGoals': 2,
    'awayGoals': 1
  },
  {
    'homeTeam': 'Pazin',
    'awayTeam': 'Labin',
    'homeGoals': 3,
    'awayGoals': 3
  }
];
}
