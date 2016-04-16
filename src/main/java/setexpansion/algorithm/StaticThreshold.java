/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setexpansion.algorithm;

import java.util.ArrayList;
import setexpansion.util.MapUtility;
import static setexpansion.util.MapUtility.DESC;
import static setexpansion.util.MapUtility.sortByComparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nawshad and sanket
 */
public class StaticThreshold {

	public static Set<String> getExpandedSet(Set<String> seeds, double alpha) {

		// Set<String> seeds = new HashSet();
		// seeds.add("playstation2");
		// seeds.add("playstation3");
		MapUtility mUtil = new MapUtility();
		Set<String> dictionary = mUtil.termCountMap.keySet();
		Map<String, Double> relScoreMap = mUtil.findRelScore(dictionary, seeds);

		Map<String, Double> sortedMapDesc = sortByComparator(relScoreMap, DESC);
		// System.out.println("Sorted Rel Scores:");
		// mUtil.printMap(sortedMapDesc);
		//
		int thresHoldIndex = mUtil.OtsusThreshold(mUtil.getValuesFromMap(sortedMapDesc));
		double thresHold = mUtil.getValuesFromMap(sortedMapDesc).get(thresHoldIndex);
		// System.out.println("Threshold:" + thresHold);
		// thresHold = 0.5416;
		Map<String, Double> topKRelScores = mUtil.findTopK(sortedMapDesc, thresHold);
		// System.out.println("Sorted Map: " + sortedMapDesc);
		// mUtil.printMap(topKRelScores);
		// System.out.println("Size of topkRelscore:" + topKRelScores.size());
		int topKSetSize = topKRelScores.size();
		// double alpha = 0.5;
		int iter = 1;
		Set<String> prevRelTerms = topKRelScores.keySet();
		String prevIterA = "";
		String prevIterR = "";
                int i = 0;
		while (true) {
			// System.out.println("\n");
			// System.out.println("Iter:" + iter);
			Map<String, Double> simScoreMap = mUtil.findRelScore(dictionary, prevRelTerms);
			// System.out.println("Sim Scores:");
			// mUtil.printMap(simScoreMap);

			Map<String, Double> gScoreMap = mUtil.findGScore(relScoreMap, simScoreMap, alpha);
			// System.out.println("g Scores:");
			// mUtil.printMap(gScoreMap);
			Map<String, Double> sortedGScoreMap = sortByComparator(gScoreMap, DESC);
			//System.out.println(sortedGScoreMap);
			Set<String> nextRelTerms = mUtil.findTopKSet(sortedGScoreMap, topKSetSize).keySet();
                        
                        if(i%100 == 0){
                            System.out.print("prevRelTerms: ");
                            Iterator it = prevRelTerms.iterator();
                            while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                            }
                            System.out.println();
                            System.out.print("nextRelTerms: ");
                            it = nextRelTerms.iterator();
                            while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                            }
                            System.out.println("\n");
                            
                        }
			
			if (MapUtility.isSetDifferent(nextRelTerms, prevRelTerms)) // if
														// different
			{
				Set diffSet = MapUtility.getDifferenceInIter(nextRelTerms, prevRelTerms);
				String a = (String) diffSet.iterator().next();
				double scoreA = sortedGScoreMap.get(a);
				String r = (String) prevRelTerms.toArray()[prevRelTerms.size() - 1];
				double scoreR = topKRelScores.get(r);

				//if (prevIterA.equalsIgnoreCase(r) && prevIterR.equalsIgnoreCase(a)) {
					if (scoreR >= scoreA) {
						nextRelTerms = prevRelTerms;
						break;
					}
				//}
				// prevRelTerms.remove(r);
				topKRelScores.remove(r);
				// Set<String> tempRelTerms = new HashSet<String>();
				// tempRelTerms.addAll(prevRelTerms);
				// tempRelTerms.add(a);
				// prevRelTerms = tempRelTerms;
				topKRelScores.put(a, scoreA);
				Map<String, Double> sortedprevRelScoreMap = sortByComparator(topKRelScores, DESC);
				prevRelTerms = sortedprevRelScoreMap.keySet();
				prevIterA = a;
				prevIterR = r;
			} else {

				break;
			}
                        i++;
			iter++;

		}
		// System.out.println("\n\n");
		// System.out.println("Term:");
		// Iterator it = prevRelTerms.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.next());
		// }
		return prevRelTerms;
	}
        
        public static void main(String args[]){
            Set <String> seeds = new HashSet();
            seeds.add("Australia");
            seeds.add("Algeria");
            Set<String> result = getExpandedSet(seeds , 0.5);
            Iterator it = result.iterator();
            while(it.hasNext()){
                System.out.print(it.next() + " ");
            }
        }
}
