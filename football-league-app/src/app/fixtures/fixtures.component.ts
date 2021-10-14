import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  matches: any[] = [{
    'homeTeam': 'Pusphaira',
    'awayTeam': 'RPC',
    'time': '15-10-2021, 10:00',
    'location': 'Dog Fields' 
  },
  {
    'homeTeam': 'Rovinj',
    'awayTeam': 'Porec',
    'time': '15-10-2021, 12:00',
    'location': 'Rovinj Stadium' 
  },
  {
    'homeTeam': 'Pazin',
    'awayTeam': 'Labin',
    'time': '15-10-2021, 14:00',
    'location': 'Pazin Stadium' 
  }
];

}
