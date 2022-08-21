require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добрый день!
        a: Я подскажу Вам адрес расположения пункта приема мусора.
        go!: /Какой мусор Вы хотели бы сдать?
        
    state: Какой мусор Вы хотели бы сдать?
        a: Какой мусор Вы хотели бы сдать?
        buttons: 
            "Стекло" -> /Отлично! Из какого Вы города?
            "Бумагу" -> /Отлично! Из какого Вы города?
            "Пластик" -> /Отлично! Из какого Вы города?
            "Смешанный мусор" -> /Отлично! Из какого Вы города?
        
    state: Отлично! Из какого Вы города?
        a: Отлично! Из какого Вы города?
        intent: /KnowledgeBase/FAQ.16_08-1 || onlyThisState = false, toState = "/Match"

    state: Bye
        intent!: /KnowledgeBase/FAQ.16_08-1
        a: Всего доброго!

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}
        go!: /Bye