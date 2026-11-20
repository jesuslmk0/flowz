package com.darkdeveloper.kawaiianimes.presentation.common

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.datasource.TransferListener
import java.io.IOException
import java.net.URL

@UnstableApi
class AdBlockingDataSourceFactory(
    private val context: Context,
    private val baseFactory: HttpDataSource.Factory = DefaultHttpDataSource.Factory()
) : HttpDataSource.Factory {

    // Dominios conocidos de publicidad/tracking en sitios de streaming anime
    val adDomains = setOf(
        "googleads.g.doubleclick.net",
        "pagead2.googlesyndication.com",
        "ads.pubmatic.com",
        "ads.rubiconproject.com",
        "ads.adfox.ru",
        "ads.yandex.ru",
        "an.yandex.ru",
        "mc.yandex.ru",
        "track.adform.net",
        "pixel.advertising.com",
        "ib.adnxs.com",
        "secure.adnxs.com",
        "ads.stickyadstv.com",
        "ads.exoclick.com",
        "syndication.exoclick.com",
        "ads.trafficjunky.net",
        "ads.juicyads.com",
        "ads.ero-advertising.com",
        "ads.plugrush.com",
        "ads.trafficforce.com",
        "ads.hilltopads.net",
        "ads.adsterra.com",
        "ads.propellerads.com",
        "ads.popads.net",
        "ads.ad-maven.com",
        "ads.adcash.com",
        "ads.adsupply.com",
        "ads.adtiger.de",
        "ads.adup-tech.com",
        "ads.adskeeper.com",
        "ads.advertise.com",
        "ads.adxpremium.com",
        "ads.avocet.io",
        "ads.bidtheatre.com",
        "ads.bidswitch.net",
        "ads.brightroll.com",
        "ads.casale.com",
        "ads.cpx.to",
        "ads.dianomi.com",
        "ads.dotomi.com",
        "ads.eyewonder.com",
        "ads.gumgum.com",
        "ads.hearst.com",
        "ads.innovid.com",
        "ads.kargo.com",
        "ads.komoona.com",
        "ads.lijit.com",
        "ads.mediavine.com",
        "ads.moatads.com",
        "ads.nativo.com",
        "ads.nexage.com",
        "ads.openx.net",
        "ads.outbrain.com",
        "ads.pubgears.com",
        "ads.pubnative.net",
        "ads.pulsepoint.com",
        "ads.quantcast.com",
        "ads.radiate.com",
        "ads.reporo.com",
        "ads.revcontent.com",
        "ads.richrelevance.com",
        "ads.rubiconproject.com",
        "ads.saymedia.com",
        "ads.smaato.net",
        "ads.smartadserver.com",
        "ads.sonobi.com",
        "ads.sovrn.com",
        "ads.specificmedia.com",
        "ads.spotxchange.com",
        "ads.stickyadstv.com",
        "ads.taboola.com",
        "ads.tremorhub.com",
        "ads.triplelift.com",
        "ads.undertone.com",
        "ads.unruly.co.uk",
        "ads.vibrantmedia.com",
        "ads.videoamp.com",
        "ads.videologygroup.com",
        "ads.visualdna.com",
        "ads.wayfair.com",
        "ads.weborama.com",
        "ads.widespace.com",
        "ads.yahoo.com",
        "ads.yieldlab.net",
        "ads.yieldmo.com",
        "ads.zedo.com",
        "adserver.adtech.de",
        "adserver.adtechus.com",
        "adserver.advertising.com",
        "adserver.ezanga.com",
        "adserver.oxado.com",
        "adserver.yieldmanager.com",
        "advertising.com",
        "analytics.yahoo.com",
        "b.scorecardresearch.com",
        "beacon.krxd.net",
        "biddingx.com",
        "cas.criteo.com",
        "cdn.adsafeprotected.com",
        "cdn.adnxs.com",
        "cdn.advertising.com",
        "cdn.cxense.com",
        "cdn.doubleverify.com",
        "cdn.krxd.net",
        "cdn.moatads.com",
        "cdn.optimizely.com",
        "cdn.permutive.com",
        "cdn.segment.com",
        "cdn.taboola.com",
        "cdn.visualdna.com",
        "click.hotjar.com",
        "cm.g.doubleclick.net",
        "collector.githubapp.com",
        "connect.facebook.net",
        "csp.secureserver.net",
        "dpm.demdex.net",
        "ds.adrecover.com",
        "dsp.adform.net",
        "e.clarity.ms",
        "edge.quantserve.com",
        "events.api.secureserver.net",
        "fast.fonts.net",
        "fonts.googleapis.com",
        "fonts.gstatic.com",
        "gc.kis.v2.scr.kaspersky-labs.com",
        "geo.yandex.ru",
        "googleads.g.doubleclick.net",
        "gtm.pubmatic.com",
        "hits.websitescripts.io",
        "ib.adnxs.com",
        "idsync.rlcdn.com",
        "in.hotjar.com",
        "insight.adsrvr.org",
        "js-agent.newrelic.com",
        "js.hs-scripts.com",
        "js.hs-analytics.net",
        "js.hs-banner.com",
        "js.hscollectedforms.net",
        "js.hsforms.net",
        "js.hubspotfeedback.com",
        "js.intercomcdn.com",
        "js.monitor.azure.com",
        "js.newrelic.com",
        "js.stripe.com",
        "l.turn.com",
        "links.services.disqus.com",
        "match.adsrvr.org",
        "match.programattik.com",
        "metrics.pubmatic.com",
        "nxs.akamaized.net",
        "pagead2.googlesyndication.com",
        "pixel.advertising.com",
        "pixel.everesttech.net",
        "pixel.mathtag.com",
        "pixel.rubiconproject.com",
        "px.ads.linkedin.com",
        "px4.ads.linkedin.com",
        "r.stripe.com",
        "r.turn.com",
        "rum-static.pingdom.net",
        "s.amazon-adsystem.com",
        "s.yimg.com",
        "sb.scorecardresearch.com",
        "secure.adnxs.com",
        "secure.cdn.fastly.net",
        "secure.gravatar.com",
        "secure.pubmatic.com",
        "secure.quantserve.com",
        "seg.service.mookie1.com",
        "siteintercept.qualtrics.com",
        "stats.g.doubleclick.net",
        "sync.adspire.me",
        "sync.mathtag.com",
        "sync.rlcdn.com",
        "tags.bluekai.com",
        "tags.crwdcntrl.net",
        "tags.expo9.exponential.com",
        "tags.tiqcdn.com",
        "tpc.googlesyndication.com",
        "track.adform.net",
        "track.hubspot.com",
        "trackcmp.net",
        "trk.kissmetrics.com",
        "trk.pinterest.com",
        "twinprime.net",
        "um.simpli.fi",
        "us.u.openx.net",
        "v2.zopim.com",
        "v4.trackjs.com",
        "w.sharethis.com",
        "widgets.outbrain.com",
        "www.google-analytics.com",
        "www.googletagmanager.com",
        "www.googletagservices.com",
        "x.bidswitch.net",
        "z.moatads.com"
    )

    override fun createDataSource(): HttpDataSource {
        return AdBlockingHttpDataSource(baseFactory.createDataSource())
    }

    override fun setDefaultRequestProperties(defaultRequestProperties: Map<String, String>): HttpDataSource.Factory {
        baseFactory.setDefaultRequestProperties(defaultRequestProperties)
        return this
    }

    inner class AdBlockingHttpDataSource(private val delegate: HttpDataSource) : HttpDataSource {

        override fun open(dataSpec: DataSpec): Long {
            val url = dataSpec.uri.toString()
            val host = try {
                URL(url).host.lowercase()
            } catch (e: Exception) {
                ""
            }

            // Verificar si el host o cualquier dominio padre es un dominio de ads
            var currentHost = host
            while (currentHost.isNotEmpty()) {
                if (currentHost in adDomains) {
                    throw IOException("Blocked ad domain: $currentHost")
                }
                val dotIndex = currentHost.indexOf('.')
                if (dotIndex == -1) break
                currentHost = currentHost.substring(dotIndex + 1)
            }

            return delegate.open(dataSpec)
        }

        override fun read(buffer: ByteArray, offset: Int, length: Int): Int = delegate.read(buffer, offset, length)

        override fun close() = delegate.close()

        override fun getUri() = delegate.uri

        override fun getResponseHeaders() = delegate.responseHeaders

        override fun addTransferListener(transferListener: TransferListener) {
            delegate.addTransferListener(transferListener)
        }

        override fun setRequestProperty(name: String, value: String) {
            delegate.setRequestProperty(name, value)
        }

        override fun clearRequestProperty(name: String) {
            delegate.clearRequestProperty(name)
        }

        override fun clearAllRequestProperties() {
            delegate.clearAllRequestProperties()
        }

        override fun getResponseCode(): Int = delegate.responseCode
    }
}