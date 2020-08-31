package akio.apps.newsreader.data.article.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class FeedEntity {
    @Element(name = "channel", type = ChannelEntity.class)
    private ChannelEntity channel;

    public ChannelEntity getChannel() {
        return channel;
    }

    public void setChannel(ChannelEntity channel) {
        this.channel = channel;
    }
}
