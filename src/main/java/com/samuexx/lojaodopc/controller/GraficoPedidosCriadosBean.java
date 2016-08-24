package com.samuexx.lojaodopc.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;


@Named
@RequestScoped
public class GraficoPedidosCriadosBean {

	private LineChartModel model;
	
	
	public void preRender(){
		this.model = new LineChartModel();
		
		this.model.setTitle("Todos os Pedidos");
		this.model.setLegendPosition("e");
		this.model.setAnimate(true);
		
		
		
		
		adicionarSerie("Todos os pedidos");
		adicionarSerie("Meus os pedidos");
		
	}

	private void adicionarSerie(String rotulo) {
		ChartSeries series = new ChartSeries(rotulo);
		
		series.set("1", Math.random() * 1000);
		series.set("2", Math.random() * 1000);
		series.set("3", Math.random() * 1000);
		series.set("4", Math.random() * 1000);
		series.set("5", Math.random() * 1000);
		
		this.model.addSeries(series);
		
	}

	public CartesianChartModel getModel() {
		return model;
	}
	
	
	
}
