package objects;

final class TestExamples {
	static final Token W = WhiteToken.instance();
	static final Token B = BlackToken.instance();

	static final Token[] ts1 = {W,W,W,W,W,W,B,W};
	static final Token[] ts2 = {W,W,W,B,W,W,B,W};
	static final Token[] ts3 = {W,B,B,W,W,W,B,W};
	static final Token[] ts4 = {W,B,B,W,W,W,B,B};
}
