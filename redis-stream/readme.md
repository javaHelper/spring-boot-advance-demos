127.0.0.1:6379> XADD purchase-events * dummy-key dummy-value
"1672231823127-0"

127.0.0.1:6379> XGROUP CREATE purchase-events purchase-events $
OK