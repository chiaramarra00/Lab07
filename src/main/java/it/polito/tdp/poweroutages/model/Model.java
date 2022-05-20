package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<PowerOutage> powerOutagesByNerc;

	private List<PowerOutage> best;
	private int bestNumberOfCustomers;

	public Model() {
		podao = new PowerOutageDAO();
	}

	public List<PowerOutage> getBest() {
		return best;
	}

	public int getBestNumberOfCustomers() {
		return bestNumberOfCustomers;
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutage> calculatePowerOutagesSubset(Nerc nerc, int x, int y) {
		powerOutagesByNerc = podao.getPowerOutagesByNerc(nerc);
		Collections.sort(powerOutagesByNerc);
		best = new ArrayList<PowerOutage>();
		bestNumberOfCustomers = 0;
		List<PowerOutage> partial = new ArrayList<PowerOutage>();
		search(partial, x, y);
		return best;
	}

	private void search(List<PowerOutage> partial, int x, int y) {
		int numberOfCustomers = calculateNumberOfCustomers(partial);
		if (numberOfCustomers > bestNumberOfCustomers) {
			best = new ArrayList<PowerOutage>(partial);
			bestNumberOfCustomers = numberOfCustomers;
		}
		for (PowerOutage po : powerOutagesByNerc) {
			if (!partial.contains(po)) {
				partial.add(po);
				long sumHours = sumHours(partial);
				int maxYear = getMaxYear(partial);
				int minYear = getMinYear(partial);
				if (sumHours <= y && (maxYear - minYear) <= x)
					search(partial, x, y);
				partial.remove(po);
			}
		}
	}

	private int calculateNumberOfCustomers(List<PowerOutage> partial) {
		int numberOfCustomers = 0;
		for (PowerOutage po : partial)
			numberOfCustomers += po.getCustomersAffected();
		return numberOfCustomers;
	}

	private int getMinYear(List<PowerOutage> partial) {
		int min = 1000000;
		for (PowerOutage po : partial)
			if (po.getDateEventBegan().getYear() < min)
				min = po.getDateEventBegan().getYear();
		return min;
	}

	private int getMaxYear(List<PowerOutage> partial) {
		int max = 0;
		for (PowerOutage po : partial)
			if (po.getDateEventFinished().getYear() > max)
				max = po.getDateEventFinished().getYear();
		return max;
	}

	public long sumHours(List<PowerOutage> result) {
		long sum = 0;
		for (PowerOutage po : result) {
			sum += po.getDuration();
		}
		return sum;
	}

}
