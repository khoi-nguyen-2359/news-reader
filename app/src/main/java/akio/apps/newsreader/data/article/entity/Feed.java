package akio.apps.newsreader.data.article.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rss")
public class Feed {
    @XmlElement(name = "channel")
    private final Channel channel;

    public Feed() {
        this(new Channel());
    }

    public Feed(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
