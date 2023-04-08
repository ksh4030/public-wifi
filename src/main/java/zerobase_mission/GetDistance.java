package zerobase_mission;

public class GetDistance {
	public double distance(String lat1, String lnt1, String lat2, String lnt2) {
		double dLat1 = Double.parseDouble(lat1);
		double dLnt1 = Double.parseDouble(lnt1);
		double dLat2 = Double.parseDouble(lat2);
		double dLnt2 = Double.parseDouble(lnt2);
		
		double theta = dLnt1 - dLnt2;
		double dist = Math.sin(deg2rad(dLat1)) * Math.sin(deg2rad(dLat2)) + Math.cos(deg2rad(dLat1)) * Math.cos(deg2rad(dLat2)) * Math.cos(deg2rad(theta));
		
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist *= 1.609344;
		
		String FDist = String.format("%.4f", dist);
		double answer = Double.parseDouble(FDist);
		return answer;
	}
	
	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	public double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
