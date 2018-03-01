package music.app;

import music.common.Composer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 04 Oct 2017
 * @author Ferenc Buzas, using an example by Josh Long
 *   from https://github.com/spring-guides/tut-bookmarks/blob/master/pom.xml
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MusicServiceApp.class)
@WebAppConfiguration
public class MusicControllerTest {
    
    private MediaType contentType = new MediaType(
                MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter json2HttpConverter;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Environment environment;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.json2HttpConverter = 
            Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull(this.json2HttpConverter);
    }

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetComposerList() throws Exception {
        mockMvc.perform(get("/music/composer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Bach")))
                .andExpect(jsonPath("$[0].birthYear", is(1685)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Haydn")))
                .andExpect(jsonPath("$[1].birthYear", is(1732)));
    }

    @Test
    public void testCreateComposer() throws Exception {
        String composerJson = json(new Composer(0, "Bach", 1756));

        this.mockMvc.perform(post("/music/composer")
                .contentType(contentType)
                .content(composerJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testComposerNotFound() throws Exception {
        mockMvc.perform(get("/music/composer")
                .content(this.json(new Composer(0, "baby", 2017)))
                .contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk());   // isNotFound() ?
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        json2HttpConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
