local key = KEYS[1]
local keyseconds = tonumber(KEYS[2])
local daycount = tonumber(KEYS[3])
local keycount = 'SmsAuthCount:'..key

local count = redis.call('GET', keycount)
  if count == false then
      redis.call('SET', keycount,1)
      redis.call('EXPIRE',keycount,keyseconds)
      return '1'
  else
      local num_count = tonumber(count)
      if num_count+1 > daycount then
         return '2'
      else
         redis.call('INCRBY',keycount,1)
         return '1'
      end
  end
