/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.h;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldAllTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutOnLibraryEffect;
import mage.constants.SubType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterBySubtypeCard;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.filter.predicate.permanent.ControllerPredicate;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author JayDi85
 */
public class ForerunnerOfTheHeralds extends CardImpl {

    private static final FilterPermanent filterYourMerfolk = new FilterPermanent("Merfolk");

    static {
        filterYourMerfolk.add(new AnotherPredicate());
        filterYourMerfolk.add(new ControllerPredicate(TargetController.YOU));
        filterYourMerfolk.add(new SubtypePredicate(SubType.MERFOLK));
    }

    public ForerunnerOfTheHeralds(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{G}");

        this.subtype.add(SubType.MERFOLK);
        this.subtype.add(SubType.SCOUT);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);


        // When Forerunner of the Heralds enters the battlefield, you may search your library for a Merfolk card, reveal it, then shuffle your library and put that card on top of it.
        this.addAbility(
                new EntersBattlefieldTriggeredAbility(
                        new SearchLibraryPutOnLibraryEffect(
                                new TargetCardInLibrary(
                                        new FilterBySubtypeCard(SubType.MERFOLK)),
                                true, true
                        ),
                true
                )
        );

        // Whenever another Merfolk enters the battlefield under your control, put a +1/+1 counter on Forerunner of the Heralds.
        this.addAbility(
                new EntersBattlefieldAllTriggeredAbility(
                        Zone.BATTLEFIELD,
                        new AddCountersSourceEffect(CounterType.P1P1.createInstance(1)),
                        filterYourMerfolk,
                        false, null, true
                )
        );
    }

    public ForerunnerOfTheHeralds(final ForerunnerOfTheHeralds card) {
        super(card);
    }

    @Override
    public ForerunnerOfTheHeralds copy() {
        return new ForerunnerOfTheHeralds(this);
    }
}