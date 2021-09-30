import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  constructor() { }

  goals: EChartsOption = {
    color: ['#673ab7'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        data: ['Vid', 'James', 'Robin', 'Jim', 'Jordie', 'Alex', 'Vincent'],
        axisTick: {
          alignWithLabel: true
        }
      }
    ],
    yAxis: [{
      type: 'value'
    }],
    series: [{
      name: 'Goals',
      type: 'bar',
      barWidth: '60%',
      data: [10, 15, 20, 5, 13, 33, 7]
    }]
  };

  assists: EChartsOption = {
    color: ['#673ab7'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        data: ['Vid', 'James', 'Robin', 'Jim', 'Jordie', 'Alex', 'Vincent'],
        axisTick: {
          alignWithLabel: true
        }
      }
    ],
    yAxis: [{
      type: 'value'
    }],
    series: [{
      name: 'Assists',
      type: 'bar',
      barWidth: '60%',
      data: [6, 4, 12, 2, 10, 10, 7]
    }]
  };

  clubGoals: EChartsOption = {
    // color: ['#673ab7'],
    tooltip: {
      trigger: 'item',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    series: [{
      name: 'Goals',
      type: 'pie',
      data: [
        {"value": 10, "name": "Pusphaira"},
        {"value": 5, "name": "RPC"},
        {"value": 2, "name": "UNA"},
        {"value": 11, "name": "Nieuw Woensel"},
        {"value": 8, "name": "Push To Talk"},
        {"value": 9, "name": "FC Eindhoven"}
      ]
    }]
  };

  goalvMiss: EChartsOption = {
  legend: {
    data: ['Goal scenarios', 'Miss scenarios']
  },
  radar: {
    // shape: 'circle',
    indicator: [
      { name: 'Free Kick', max: 50 },
      { name: 'Penalty', max: 50 },
      { name: 'Corner', max: 50 },
      { name: 'Counter Attack', max: 50 },
      { name: 'Solo Run', max: 50 },
    ]
  },
  series: [
    {
      name: 'Goal vs Miss',
      type: 'radar',
      data: [
        {
          value: [15, 5, 7, 35, 25],
          name: 'Goal scenarios'
        },
        {
          value: [45, 5, 38, 8, 6],
          name: 'Miss scenarios'
        }
      ]
    }
  ]
};

transferNumbers: EChartsOption = {
  xAxis: {
    type: 'category',
    data: ['Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [0, 10, 15, 2, 23, 2, 11],
      type: 'line'
    }
  ]
};

  ngOnInit(): void {}
  
}
