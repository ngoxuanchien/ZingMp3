package zingmp3;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.observation.Observation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;
import zingmp3.dto.SongDto;
import zingmp3.model.*;
import zingmp3.repository.GenreRepository;
import zingmp3.repository.PreviewInfoRepository;
import zingmp3.repository.SongRepository;
import zingmp3.repository.StreamingRepository;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8082", description = "song-service server"),
				@Server(url = "http://localhost:8081", description = "api-gateway server"),
				@Server(url = "http://nxc-hcmus.me:8081", description = "Production server")
		},
		info = @Info(title = "Song Service API", version = "1.0", description = "API for Song Service")
)
//@SecurityScheme(
//		name = "Keycloak",
//		type = SecuritySchemeType.HTTP,
//		bearerFormat = "JWT",
//		scheme = "bearer"
//)
@SecurityScheme(
		name = "Keycloak",
		type = SecuritySchemeType.OAUTH2,
		flows = @OAuthFlows(
				password = @OAuthFlow(
//						tokenUrl = "http://localhost:8080/realms/zing-mp3/protocol/openid-connect/token",
//						tokenUrl = "http://nxc-hcmus.me:8081/api/auth/token",
						tokenUrl = "${keycloak.tokenUrl}",
						refreshUrl = "${keycloak.tokenUrl}",
						scopes = {
								@OAuthScope(name = "openid", description = "openid scope")
						}
				)
		)
)
public class SongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongServiceApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner commandLineRunner(
//			SongRepository songRepository
//	) {
//
//		return args -> {
//			Song song1 = Song.builder()
//					.songName("Âm thầm bên em (Single)")
//					.thumbnail("https://i.ytimg.com/vi/30KI5SuECuc/hqdefault.jpg")
//					.songWriter("Sơn Tùng M-TP")
//					.lyric("Bài Hát: Âm Thầm Bên Em\n" +
//							"Ca Sĩ: Sơn Tùng M-TP\n" +
//							"Khi bên anh em thấy điều chi\n" +
//							"Khi bên anh em thấy điều gì\n" +
//							"Nước mắt rơi\n" +
//							"Gần kề làn mi\n" +
//							"Chẳng còn những giây phút\n" +
//							"Chẳng còn những ân tình\n" +
//							"Gió mang em rời xa nơi đây\n" +
//							"Khi xa anh em nhớ về ai\n" +
//							"Khi xa anh em nhớ\n" +
//							"Một người chắc không phải\n" +
//							"Một người như anh\n" +
//							"Người từng làm em khóc\n" +
//							"Người từng khiến em buồn\n" +
//							"Buông bàn tay\n" +
//							"Rời xa lặng thinh bước đi\n" +
//							"Hạt mưa rơi bủa vây\n" +
//							"Trái tim hiu quạnh\n" +
//							"Ngàn yêu thương\n" +
//							"Vụt tan bỗng xa\n" +
//							"Người từng nói ở bên\n" +
//							"Cạnh anh mỗi khi anh buồn\n" +
//							"Cớ sao giờ\n" +
//							"Lời nói kia như gió bay\n" +
//							"Đừng bỏ rơi\n" +
//							"Bàn tay ấy bơ vơ mà\n" +
//							"Một mình anh lặng im chốn đây\n" +
//							"Yêu em âm thầm bên em\n" +
//							"Yêu thương không còn nơi đây\n" +
//							"Em mang tình buồn theo mây\n" +
//							"Cơn mơ về mong manh câu thề\n" +
//							"Tan trôi qua mau\n" +
//							"Quên đi phút giây\n" +
//							"Mưa rơi trên đôi mi qua lối vắng\n" +
//							"Ánh sáng mờ\n" +
//							"Buông lơi làn khói trắng\n" +
//							"Bóng dáng em\n" +
//							"Nụ cười ngày hôm qua\n" +
//							"Ký ức có ngủ quên\n" +
//							"Chìm trong màn sương đắng\n" +
//							"Anh nhớ giọt nước mắt sâu lắng\n" +
//							"Anh nhớ nỗi buồn\n" +
//							"Của em ngày không nắng\n" +
//							"Buông bàn tay\n" +
//							"Rời xa lặng thinh bước đi\n" +
//							"Hạt mưa rơi bủa vây\n" +
//							"Trái tim hiu quạnh\n" +
//							"Ngàn yêu thương\n" +
//							"Vụt tan bỗng xa\n" +
//							"Người từng nói ở bên\n" +
//							"Cạnh anh mỗi khi anh buồn\n" +
//							"Cớ sao giờ\n" +
//							"Lời nói kia như gió bay\n" +
//							"Bàn tay bơ vơ mà\n" +
//							"Cầm bông hoa chờ mong nhớ thương\n" +
//							"Làm sao quên người ơi\n" +
//							"Tình anh mãi như hôm nào\n" +
//							"Vẫn yêu người\n" +
//							"Và vẫn mong em về đây\n" +
//							"Giọt nước mắt\n" +
//							"Tại sao cứ lăn rơi hoài\n" +
//							"Ở bên anh chỉ có đớn đau\n" +
//							"Thì anh xin nhận hết\n" +
//							"Ngàn đau đớn để thấy em cười\n" +
//							"Dẫu biết rằng\n" +
//							"Người đến không như giấc mơ\n" +
//							"Yêu em âm thầm bên em\n" +
//							"Yêu em âm thầm bên em\n" +
//							"Thì anh xin nhận hết\n" +
//							"Ngàn đau đớn để thấy em cười\n" +
//							"Dẫu biết rằng\n" +
//							"Người đến không như giấc mơ\n" +
//							"Yêu em âm thầm bên em")
//					.duration(291)
//					.providedBy("VIVI ENM")
//					.liked(83)
//					.played(10)
//					.build();
//
//			songRepository.save(song1);
//
//			Song song2 = Song.builder()
//					.songName("Có chàng trai viết lên cây")
//					.thumbnail("https://i.ytimg.com/vi/0VC6euBtKkk/maxresdefault.jpg")
//					.songWriter("Phan Mạnh Quỳnh")
//					.lyric("Bài hát: Có Chàng Trai Viết Lên Cây\n" +
//							"Ca sĩ: Phan Mạnh Quỳnh\n" +
//							"Có chàng trai viết lên cây\n" +
//							"Lời yêu thương cô gái ấy\n" +
//							"Mối tình như gió như mây\n" +
//							"Nhiều năm trôi qua vẫn thấy\n" +
//							"Giống như bức tranh\n" +
//							"Vẽ bằng dịu êm ngày thơ\n" +
//							"Có khi trong tiềm thức ngỡ là mơ\n" +
//							"Câu chuyện đã rất xa xôi\n" +
//							"Niềm riêng không ai biết tới\n" +
//							"Hai người sống ở hai nơi\n" +
//							"Từ lâu không đi sát lối\n" +
//							"Chỉ thương có người vẫn hoài\n" +
//							"Gìn giữ nhiều luyến lưu\n" +
//							"Mỗi khi nhớ đôi mắt biếc\n" +
//							"Như thời chưa biết buồn đau\n" +
//							"Ngày cô ấy đi theo chân mẹ cha\n" +
//							"Chàng trai bơ vơ từ xa\n" +
//							"Trong tim hụt hẫng\n" +
//							"Như mất một thứ gì\n" +
//							"Không ai hiểu thấu\n" +
//							"Vì tình yêu những đứa trẻ con thì\n" +
//							"Vu vơ nhanh qua đâu nghĩ gieo\n" +
//							"Tương tư đến dài như thế\n" +
//							"Đời muôn ngả\n" +
//							"Mang số kiếp đổi thay\n" +
//							"Rồi khi tình cờ gặp lại\n" +
//							"Hai thân phận khác\n" +
//							"Dẫu tên người vẫn vậy\n" +
//							"Có một người vẫn vậy\n" +
//							"Thì ra xa nhau là mất thôi\n" +
//							"Tay không chung đôi\n" +
//							"Chỉ giấc mơ vẫn còn\n" +
//							"Bồi hồi trọn đời\n" +
//							"Có chàng trai lúc xuân xanh\n" +
//							"Ngược xuôi bon chen đất khách\n" +
//							"Mối tình cứ thế phai nhanh\n" +
//							"Dường như thôi không nghĩ đến\n" +
//							"Ít lâu có cô gái\n" +
//							"Làm dịu êm hồn đã khô\n" +
//							"Dẫu không có đôi mắt\n" +
//							"Giống mùa thu\n" +
//							"Câu chuyện đáng lẽ xa xôi\n" +
//							"Niềm riêng không ai nhắc tới\n" +
//							"Nhưng rồi ngăn cách xa khơi\n" +
//							"Một hôm cơn mưa dẫn lối\n" +
//							"Thấy cô gái năm ấy\n" +
//							"Khiến thổn thức như lúc đầu\n" +
//							"Vẫn nơi đó đôi mắt biếc\n" +
//							"Nhưng giờ đã biết buồn đau\n" +
//							"Ngày cô ấy đi theo chân mẹ cha\n" +
//							"Chàng trai bơ vơ từ xa\n" +
//							"Trong tim hụt hẫng\n" +
//							"Như mất một thứ gì\n" +
//							"Không ai hiểu thấu\n" +
//							"Vì tình yêu những đứa trẻ con thì\n" +
//							"Vu vơ nhanh qua đâu nghĩ gieo\n" +
//							"Tương tư đến dài như thế\n" +
//							"Đời muôn ngả\n" +
//							"Mang số kiếp đổi thay\n" +
//							"Rồi khi tình cờ gặp lại\n" +
//							"Hai thân phận khác\n" +
//							"Dẫu tên người vẫn vậy\n" +
//							"Có một người vẫn vậy\n" +
//							"Thì ra xa nhau là mất thôi\n" +
//							"Tay không chung đôi\n" +
//							"Chỉ giấc mơ vẫn còn\n" +
//							"Bồi hồi trọn đời\n" +
//							"Có chàng trai viết lên cây\n" +
//							"Lời yêu thương cô gái ấy\n" +
//							"Có chàng trai viết lên cây\n" +
//							"Lời yêu thương cô gái ấy")
//					.duration(310)
//					.providedBy("Brandbeats")
//					.liked(83)
//					.played(10)
//					.build();
//
//			songRepository.save(song2);
//
//
//			Song song3 = Song.builder()
//					.songName("Lạc trôi")
//					.thumbnail("https://upload.wikimedia.org/wikipedia/vi/5/5d/Lac_troi_single_sontungmtp.jpg")
//					.songWriter("Sơn Tùng M-TP")
//					.lyric("Bài hát: Lạc Trôi\n" +
//							"Ca sĩ: Sơn Tùng M-TP\n" +
//							"Người theo hương hoa\n" +
//							"Mây mù giăng lối\n" +
//							"Làn sương khói phôi phai\n" +
//							"Đưa bước ai xa rồi\n" +
//							"Đơn côi mình ta vấn vương\n" +
//							"Hồi ức trong men say\n" +
//							"Chiều mưa buồn\n" +
//							"Ngăn giọt lệ ngừng\n" +
//							"Khiến khoé mi sầu bi\n" +
//							"Đường xưa nơi cố nhân\n" +
//							"Từ giã biệt li\n" +
//							"Cánh hoa rụng rời\n" +
//							"Phận duyên mong manh\n" +
//							"Rẽ lối trong mơ ngày tương phùng\n" +
//							"Tiếng khóc cuốn theo làn gió bay\n" +
//							"Thuyền ai qua sông\n" +
//							"Lỡ quên vớt ánh trăng tàn nơi này\n" +
//							"Trống vắng bóng ai dần hao gầy\n" +
//							"Lòng ta xin nguyện khắc ghi\n" +
//							"Trong tim tình nồng mê say\n" +
//							"Mặc cho tóc mây\n" +
//							"Vương lên đôi môi cay\n" +
//							"Bâng khuâng mình ta lạc trôi giữa đời\n" +
//							"Ta lạc trôi giữa trời\n" +
//							"Đôi chân lang thang về nơi đâu\n" +
//							"Bao yêu thương giờ nơi đâu\n" +
//							"Câu thơ tình xưa vội phai mờ\n" +
//							"Theo làn sương tan biến trong cõi mơ\n" +
//							"Mưa bụi vương trên làn mi mắt\n" +
//							"Ngày chia lìa hoa rơi buồn hiu hắt\n" +
//							"Tiếng đàn ai thêm sầu tương tư\n" +
//							"Lặng mình trong chiều hoàng hôn\n" +
//							"Tan vào lời ca\n" +
//							"Lối mòn đường vắng một mình ta\n" +
//							"Nắng chiều vàng úa nhuộm ngày qua\n" +
//							"Xin đừng quay lưng xoá\n" +
//							"Đừng mang câu hẹn ước kia rời xa\n" +
//							"Yên bình nơi nào đây\n" +
//							"Chôn vùi theo làn mây\n" +
//							"Người theo hương hoa\n" +
//							"Mây mù giăng lối\n" +
//							"Làn sương khói phôi phai\n" +
//							"Đưa bước ai xa rồi\n" +
//							"Đơn côi mình ta vấn vương\n" +
//							"Hồi ức trong men say\n" +
//							"Chiều mưa buồn\n" +
//							"Ngăn giọt lệ ngừng\n" +
//							"Khiến khoé mi sầu bi\n" +
//							"Đường xưa nơi cố nhân\n" +
//							"Từ giã biệt li\n" +
//							"Cánh hoa rụng rời\n" +
//							"Phận duyên mong manh\n" +
//							"Rẽ lối trong mơ ngày tương phùng\n" +
//							"Tiếng khóc cuốn theo làn gió bay\n" +
//							"Thuyền ai qua sông\n" +
//							"Lỡ quên vớt ánh trăng tàn nơi này\n" +
//							"Trống vắng bóng ai dần hao gầy\n" +
//							"Lòng ta xin nguyện khắc ghi\n" +
//							"Trong tim tình nồng mê say\n" +
//							"Mặc cho tóc mây\n" +
//							"Vương lên đôi môi cay\n" +
//							"Bâng khuâng mình ta lạc trôi giữa đời\n" +
//							"Ta lạc trôi giữa trời\n" +
//							"Ta lạc trôi\n" +
//							"Lạc trôi\n" +
//							"Ta lạc trôi giữa đời\n" +
//							"Lạc trôi giữa trời\n" +
//							"Ta đang lạc nơi nào\n" +
//							"Ta đang lạc nơi nào\n" +
//							"Lối mòn đường vắng một mình ta\n" +
//							"Ta đang lạc nơi nào\n" +
//							"Nắng chiều vàng úa nhuộm ngày qua\n" +
//							"Ta đang lạc nơi nào")
//					.duration(233)
//					.providedBy("VIVI ENM")
//					.liked(83)
//					.played(10)
//					.build();
//
//			songRepository.save(song3);
//
//			Song song4 = Song.builder()
//					.songName("Làm Người Yêu Em Nhé Baby")
//					.thumbnail("https://photo-resize-zmp3.zadn.vn/w600_r1x1_jpeg/covers/8/d/8d5e480dd59b941af0ae87c5f055482d_1474439572.jpg")
//					.songWriter("Thảo Wendy")
//					.lyric("Bài Hát: Làm Người Yêu Em Nhé Baby\n" +
//							"Ca Sĩ: Wendy Thảo\n" +
//							"Người yêu ơi\n" +
//							"Yêu mình em được không\n" +
//							"Từ giờ và sau này\n" +
//							"Xua lạnh nơi đây mùa đông\n" +
//							"Là ngày ta sum vầy\n" +
//							"Con tim hao gầy\n" +
//							"Tình yêu đong đầy\n" +
//							"Hãy để em chứng minh cho anh thấy\n" +
//							"Người yêu ơi yêu thì có gì sai\n" +
//							"Đâu có sai đâu\n" +
//							"Không là em thì ai\n" +
//							"Để em đưa anh về\n" +
//							"Là tình yêu mãi mê\n" +
//							"Mặc kệ người ta cười chê\n" +
//							"Bởi vì tình yêu là thế\n" +
//							"Làm người yêu em nhé Babe\n" +
//							"Anh lặng im còn em thì\n" +
//							"Anh lặng im\n" +
//							"Còn em thì ngồi  ngẩn ngơ\n" +
//							"Nhìn khuôn mặt ngây thơ đó\n" +
//							"Anh hững hờ quá nên\n" +
//							"Em lo sợ bơ vơ\n" +
//							"Đợi chờ đôi mắt\n" +
//							"Ngoan anh ngại ngùng ngó sang\n" +
//							"Em thẫn thờ bơ phờ đôi mắt mơ\n" +
//							"Em đã cố đổi thay\n" +
//							"Để mạnh mẽ hơn\n" +
//							"Vì em sợ cô đơn\n" +
//							"Nói yêu má hồng mắt cong\n" +
//							"Bờ môi mọng rất khó\n" +
//							"Vì lo trong trái tim anh đã có ai\n" +
//							"Nhưng nếu yêu anh là sai\n" +
//							"Thì em đây không cần đúng\n" +
//							"Người yêu ơi\n" +
//							"Yêu mình em được không\n" +
//							"Từ giờ và sau này\n" +
//							"Xua lạnh nơi đây mùa đông\n" +
//							"Là ngày ta sum vầy\n" +
//							"Con tim hao gầy\n" +
//							"Tình yêu đong đầy\n" +
//							"Hãy để em chứng minh cho anh thấy\n" +
//							"Người yêu ơi yêu thì có gì sai\n" +
//							"Đâu có sai đâu\n" +
//							"Không là em thì ai\n" +
//							"Để em đưa anh về\n" +
//							"Là tình yêu mãi mê\n" +
//							"Mặc kệ người ta cười chê\n" +
//							"Bởi vì tình yêu là thế\n" +
//							"Làm người yêu em nhé Babe\n" +
//							"Rồi làm chồng em nhé babe\n" +
//							"Người yêu ơi\n" +
//							"Yêu mình em được không\n" +
//							"Từ giờ và sau này\n" +
//							"Xua lạnh nơi đây mùa đông\n" +
//							"Là ngày ta sum vầy\n" +
//							"Con tim hao gầy\n" +
//							"Tình yêu đong đầy\n" +
//							"Hãy để em chứng minh cho anh thấy\n" +
//							"Người yêu ơi yêu thì có gì sai\n" +
//							"Đâu có sai đâu\n" +
//							"Không là em thì ai\n" +
//							"Để em đưa anh về\n" +
//							"Là tình yêu mãi mê\n" +
//							"Mặc kệ người ta cười chê\n" +
//							"Bởi vì tình yêu là thế\n" +
//							"Làm người yêu em nhé Babe\n" +
//							"Rồi làm chồng em nhé babe\n" +
//							"Em đã cố đổi thay\n" +
//							"Để mạnh mẽ hơn\n" +
//							"Vì em sợ cô đơn\n" +
//							"Là khi những nỗi nhớ\n" +
//							"Như sóng xô nơi đây từng cơn\n" +
//							"Là khi anh cười đùa bên ai\n" +
//							"Đau đớn nên em giận hờn\n" +
//							"Nhưng yêu anh là sai\n" +
//							"Em không cần đúng\n" +
//							"Người yêu ơi\n" +
//							"Yêu mình em được không\n" +
//							"Từ giờ và sau này\n" +
//							"Xua lạnh nơi đây mùa đông\n" +
//							"Là ngày ta sum vầy\n" +
//							"Con tim hao gầy\n" +
//							"Tình yêu đong đầy\n" +
//							"Hãy để em chứng minh cho anh thấy\n" +
//							"Người yêu ơi yêu thì có gì sai\n" +
//							"Đâu có sai đâu\n" +
//							"Không là em thì ai\n" +
//							"Để em đưa anh về\n" +
//							"Là tình yêu mãi mê\n" +
//							"Mặc kệ người ta cười chê\n" +
//							"Bởi vì tình yêu là thế\n" +
//							"Làm người yêu em nhé Babe\n" +
//							"Rồi làm chồng em nhé babe\n" +
//							"Làm người yêu em nhé Babe\n" +
//							"Rồi làm chồng em nhé babe\n" +
//							"Ý anh sao?\n" +
//							"Ý anh sao?")
//					.duration(245)
//					.providedBy("VIVI ENM")
//					.liked(83)
//					.played(10)
//					.build();
//
//			songRepository.save(song4);
//
//			Song song5 = Song.builder()
//					.songName("Em Xinh")
//					.thumbnail("https://photo-resize-zmp3.zadn.vn/w600_r1x1_jpeg/covers/8/d/8d5e480dd59b941af0ae87c5f055482d_1474439572.jpg")
//					.songWriter("Mono")
//					.lyric("Lấp lánh như những vì sao\n" +
//							"Chiếu sáng bay trên trời cao\n" +
//							"Thấp thoáng có bóng hình ai\n" +
//							"Nụ cười tựa như nắng\n" +
//							"Dịu dàng và xinh xắn\n" +
//							"Thướt tha duyên dáng\n" +
//							"Tinh khôi chính là em\n" +
//							"Đôi môi kia đậm đà\n" +
//							"Và tươi như ban mai\n" +
//							"Mái tóc nhẹ buông trên vai\n" +
//							"Hương thơm êm dịu\n" +
//							"Nồng nàn như bông hoa\n" +
//							"Bướm vây ong bay lượn quanh\n" +
//							"Yêu kiều và thật mỹ miều\n" +
//							"Vui tươi hồn nhiên ôi thật đáng yêu\n" +
//							"Giọng điệu ngọt ngào đầy hơi ấm\n" +
//							"Hỡi ơi cô gái trong mơ ôi nàng thơ\n" +
//							"Em xinh em xinh\n" +
//							"Em xinh đẹp và lung linh\n" +
//							"Vô tư ngân nga\n" +
//							"Múa ca dưới ánh bình minh\n" +
//							"Em xinh em xinh\n" +
//							"Em xinh đẹp và lung linh\n" +
//							"Không gian hoá thành\n" +
//							"Bức tranh mỗi khi có em\n" +
//							"Nàng đẹp xao xuyến ơ\n" +
//							"Cái tâm cái tư cái nết người\n" +
//							"Ý a ý a a ý a\n" +
//							"Không son cũng chẳng phấn\n" +
//							"Khiến trăng e thẹn hoa nhường\n" +
//							"Nghiêng nước nghiêng thành\n" +
//							"Gió lao xao mây lao đao\n" +
//							"Mỗi khi em cười\n" +
//							"Mộc mạc nhưng thật dễ chịu\n" +
//							"Thiên sắc tinh hoa của đất trời\n" +
//							"Châu báu chẳng quý bằng em\n" +
//							"Ngọc ngà sao mà sánh\n" +
//							"Đôi môi kia đậm đà\n" +
//							"Và tươi như ban mai\n" +
//							"Mái tóc nhẹ buông trên vai\n" +
//							"Hương thơm êm dịu\n" +
//							"Nồng nàn như bông hoa\n" +
//							"Bướm vây ong bay lượn quanh\n" +
//							"Yêu kiều và thật mỹ miều\n" +
//							"Vui tươi hồn nhiên ôi thật đáng yêu\n" +
//							"Giọng điệu ngọt ngào đầy hơi ấm\n" +
//							"Hỡi ơi cô gái trong mơ ôi nàng thơ\n" +
//							"Em xinh em xinh\n" +
//							"Em xinh đẹp và lung linh\n" +
//							"Vô tư ngân nga\n" +
//							"Múa ca dưới ánh bình minh\n" +
//							"Em xinh em xinh\n" +
//							"Em xinh đẹp và lung linh\n" +
//							"Không gian hoá thành\n" +
//							"Bức tranh mỗi khi có em\n" +
//							"Em xinh\n" +
//							"Em xinh đẹp và lung linh\n" +
//							"Múa ca dưới ánh bình minh\n" +
//							"Không gian hoá thành\n" +
//							"Bức tranh mỗi khi có em")
//					.duration(183)
//					.providedBy("Ingrooves Music Group")
//					.liked(83)
//					.played(10)
//					.build();
//
//			songRepository.save(song5);
//
//			Song song6 = Song.builder()
//					.songName("Cắt đôi nỗi sầu")
//					.thumbnail("https://i.ytimg.com/vi/GpmOn4RyzZI/maxresdefault.jpg")
//					.songWriter("Tăng Duy Tân")
//					.lyric("Cắt đôi nỗi sầu\n" +
//							"Anh buông tay cắt đôi nỗi sầu\n" +
//							"Anh cắt đi cả bóng hình\n" +
//							"Anh mang theo bên mình bấy lâu\n" +
//							"Nỗi đau đã cạn\n" +
//							"Cơn mưa trong tim cũng đã tan\n" +
//							"Anh bán đi mọi nỗi buồn\n" +
//							"Để chẳng còn gì ngoài thanh thản\n" +
//							"Em ơi anh muốn\n" +
//							"Mỗi tối đến anh không phải thất tình\n" +
//							"Muốn quên một bóng hình\n" +
//							"Em để lại, trong tim\n" +
//							"Anh không thể đếm\n" +
//							"Đã có bấy nhiêu đêm phải kiếm tìm\n" +
//							"Kiếm thêm một lí do\n" +
//							"Cho cuộc tình không tên\n" +
//							"Anh đã thức, thức xuyên đêm\n" +
//							"Anh đã cố gắng để quên em\n" +
//							"Nhưng anh biết\n" +
//							"Trong con tim anh không đành\n" +
//							"Màn đêm xuống, muốn buông tay\n" +
//							"Sầu giăng kín nỗi nhớ đong đầy\n" +
//							"Anh đang chết dần ngày từng ngày\n" +
//							"Em ơi\n" +
//							"Nhớ em rất nhiều\n" +
//							"Em ơi anh nhớ em rất nhiều\n" +
//							"Anh nhớ hơn cả nhớ nhà\n" +
//							"Nhưng em đâu nhớ gì đến ta\n" +
//							"Lúc yêu chẳng hiểu\n" +
//							"Khi chia li sẽ đau rất nhiều\n" +
//							"Đau đến trong tận linh hồn\n" +
//							"Và cuộc đời một màu băng giá\n" +
//							"Em ơi anh muốn\n" +
//							"Mỗi tối đến anh không phải thất tình\n" +
//							"Muốn quên một bóng hình\n" +
//							"Em để lại, trong tim\n" +
//							"Anh không thể đếm\n" +
//							"Đã có bấy nhiêu đêm phải kiếm tìm\n" +
//							"Kiếm thêm một lí do\n" +
//							"Cho cuộc tình không tên\n" +
//							"Anh đã thức, thức xuyên đêm\n" +
//							"Anh đã cố gắng để quên em\n" +
//							"Nhưng anh biết\n" +
//							"Trong con tim anh không đành\n" +
//							"Màn đêm xuống, muốn buông tay\n" +
//							"Sầu giăng kín nỗi nhớ đong đầy\n" +
//							"Anh đang chết dần ngày từng ngày\n" +
//							"Em ơi")
//					.duration(183)
//					.providedBy("Euphoria Media Vietnam")
//					.liked(83)
//					.played(10)
//					.build();
//
//			songRepository.save(song6);

//		};
//	}

}
