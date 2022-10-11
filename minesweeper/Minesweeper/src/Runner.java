import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Runner {
	private static HttpURLConnection con;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board minesweeper = new Board();
		System.out.println("Darnit");
		String url = "https://apcsapractice.com/covid/";

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			con.setRequestMethod("GET");
			StringBuilder content;

			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

				String line;
				content = new StringBuilder();

				while ((line = in.readLine()) != null) {

					content.append(line);
					content.append(System.lineSeparator());
				}
				System.out.println(content.toString());

			}catch(Exception e) {
				System.out.println("no go");
			}

		} catch(Exception e) {
			System.out.println("error");
			con.disconnect();
		}
	}

}
