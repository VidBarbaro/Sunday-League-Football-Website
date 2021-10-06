import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TeamManager } from 'src/app/model/TeamManager';

@Component({
  selector: 'app-managers',
  templateUrl: './managers.component.html',
  styleUrls: ['./managers.component.css']
})
export class ManagersComponent implements OnInit {

  players: TeamManager[] = [];

  constructor(private http: HttpClient) { }

  displayedColumns: string[] = ['firstName', 'lastName', 'country', 'club'];
  dataSource!: MatTableDataSource<TeamManager>;

  ngOnInit(): void {
    this.getAllPlayers();
  }

  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  public getAllPlayers() {
    let url = "http://localhost:8080/teamManagers";
    this.http.get<TeamManager[]>(url).subscribe(
      res => {
        this.players = res;
        this.dataSource = new MatTableDataSource(this.players);
      },
      err => {
        alert("An error has occured;")
      }
    )
  }
}