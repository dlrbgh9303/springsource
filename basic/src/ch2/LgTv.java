package ch2;

public class LgTv implements TV {
	@Override
	public void turnOn() {
		System.out.println("LgTv - 전원 On");
	}
	@Override
	public void turnOff() {
		System.out.println("LgTv - 전원 Off");
	}
	@Override
	public void soundUp() {
		System.out.println("LgTv - 볼륨 Up");
	}
	@Override
	public void soundOff() {
		System.out.println("LgTv - 볼륨 Off");
	}
}
