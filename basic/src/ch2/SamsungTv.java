package ch2;

public class SamsungTv implements TV{
	@Override
	public void turnOn() {
		System.out.println("SamsungTv - 파워 On");
		
	}
	@Override
	public void turnOff() {
		System.out.println("SamsungTv - 파워 Off");
		
	}
	@Override
	public void soundUp() {
		System.out.println("SamsungTv - 볼륨 Up");
		
	}
	@Override
	public void soundOff() {
		System.out.println("SamsungTv - 볼륨 Down");
		
	}
}
