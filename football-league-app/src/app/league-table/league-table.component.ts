import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

export interface PeriodicElement {
  position: number;
  club: string;
  wins: number;
  draws: number;
  loses: number;
  points: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, club: 'Best Vooruit', wins: 1, draws: 2, loses: 1, points: 15},
  {position: 2, club: 'SV Budel', wins: 4, draws: 2, loses: 1, points: 15},
  {position: 3, club: 'DBS', wins: 6, draws: 2, loses: 1, points: 15},
  {position: 4, club: 'Dommelen', wins: 9, draws: 2, loses: 1, points: 15},
  {position: 5, club: 'DVS', wins: 10, draws: 2, loses: 1, points: 15},
  {position: 6, club: 'Oirschot Vooruit', wins: 12, draws: 2, loses: 1, points: 15},
  {position: 7, club: 'Pusphaira', wins: 14, draws: 2, loses: 1, points: 15},
  {position: 8, club: 'RKVVO', wins: 15, draws: 2, loses: 1, points: 15},
  {position: 9, club: 'RPC', wins: 18, draws: 2, loses: 1, points: 15},
  {position: 10, club: 'SBC', wins: 20, draws: 2, loses: 1, points: 15},
  {position: 11, club: 'UNA', wins: 20, draws: 2, loses: 1, points: 15},
  {position: 12, club: 'SV Valkenswaard', wins: 20, draws: 2, loses: 1, points: 15},
];

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {
  displayedColumns: string[] = ['position', 'club', 'wins', 'draws', 'loses', 'points'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }
}
