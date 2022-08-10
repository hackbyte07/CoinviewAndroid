package com.hackbyte.coinview.data.mapper

import coingecko.models.coins.CoinFullData
import com.hackbyte.coinview.domain.models.coins.coins_id.*


fun CoinFullData.toCoinsId(): CoinsId = CoinsId(
    block_time_in_minutes = (this.blockTimeInMinutes).toInt(),
    categories = categories,
    coingecko_rank = coingeckoRank.toInt(),
    coingecko_score = coingeckoScore,
    community_score = communityScore,
    description =
    Description(en = this.description.map { it.value }),
    id = id,
    image = Image(image.large ?: "", image.small, image.thumb),
    last_updated = lastUpdated ?: "",
    liquidity_score = liquidityScore,
    market_cap_rank = marketCapRank.toInt(),
    name = name,
    symbol = symbol,
    tickers = tickers?.map {
        Ticker(
            base = it.base, bid_ask_spread_percentage = it.bidAskSpreadPercentage ?: 0.0,
            coin_id = it.coinId, converted_last = ConvertedLast(
                it.convertedLast.values.elementAt(0),
                it.convertedLast.values.elementAt(1),
                it.convertedLast.values.elementAt(2)
            ),
            converted_volume = ConvertedVolume(
                it.convertedVolume.values.elementAt(0),
                it.convertedVolume.values.elementAt(1),
                it.convertedVolume.values.elementAt(2)
            ),
            is_anomaly = it.isAnomaly, is_stale = it.isStale, last = it.last,
            last_fetch_at = it.lastFetchAt, last_traded_at = it.lastTradedAt,
            market = Market(it.market.hasTradingIncentive, it.market.identifier, it.market.name),
            target = it.target, target_coin_id = it.targetCoinId.toString(), it.timestamp,
            trade_url = it.tradeUrl.toString(), it.trustScore.toString(), it.volume
        )
    } ?: emptyList()
)